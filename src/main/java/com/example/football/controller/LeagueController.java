package com.example.football.controller;

import com.example.football.dto.Request.LeagueRequest;
import com.example.football.dto.Request.ScheduleMatchRequest;
import com.example.football.entity.League;
import com.example.football.repo.LeagueRepository;
import com.example.football.service.LeagueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/Api/V1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LeagueController {
    private final LeagueService leagueService;

    private final LeagueRepository leagueRepository;
    private static String UPLOAD_DIR = "uploads/";

    @GetMapping("/teamleague/{idLeague}")
    public ResponseEntity<?> teamleague(@PathVariable Integer idLeague){
        return new ResponseEntity<>(leagueService.teamleague(idLeague), HttpStatus.OK);
    }

    @GetMapping("/teamleague")
    public ResponseEntity<?> teamleague(){
        return new ResponseEntity<>(leagueService.teamleague(), HttpStatus.OK);
    }

    @GetMapping("/allleague")
    public ResponseEntity<?> allleague(){
        return new ResponseEntity<>(leagueService.getLeagues(),HttpStatus.OK);
    }

    @PostMapping("chooseopenteam")
    public ResponseEntity<?> schedulematch(@RequestBody ScheduleMatchRequest scheduleMatchRequest){
        return new ResponseEntity<>(leagueService.schedulematch(scheduleMatchRequest),HttpStatus.CREATED);
    }

    @GetMapping("/schedulematch")
    public ResponseEntity<?> schedulematch(){
        return new ResponseEntity<>(leagueService.getschedulematch(),HttpStatus.OK);
    }

    @PostMapping(value = "/addleague",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addleague(@RequestPart("league") String leagueJson,@RequestPart("rules") MultipartFile rulesFile) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        LeagueRequest leagueRequest = objectMapper.readValue(leagueJson, LeagueRequest.class);
        String fileName = rulesFile.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        rulesFile.transferTo(filePath);
        return new ResponseEntity<>(leagueService.addLeague(leagueRequest,fileName),HttpStatus.CREATED );
    }

    @GetMapping("/get-rules/{idLeague}")
    public ResponseEntity<?> getRules(@PathVariable Integer idLeague) throws IOException {
        Optional<League> league = leagueRepository.findById(idLeague);

        if (!league.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        League league1 = league.get();
        String rulesFilePath = league1.getDieule();
        File file = new File(rulesFilePath);
        return new ResponseEntity<>(URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.toString()),HttpStatus.OK);

//        if (!file.exists()) {
//            return ResponseEntity.notFound().build();
//        }

//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
//                .contentType(MediaType.APPLICATION_PDF)
//                .contentLength(file.length())
//                .body(resource);
    }
    @GetMapping("/get-file")
    public ResponseEntity<InputStreamResource> getFile(@RequestParam("filepath") String encodedFilePath) throws IOException {
        // Giải mã tên file từ URL
        String filePathStr = URLDecoder.decode(encodedFilePath, StandardCharsets.UTF_8.toString());

        // Tạo đường dẫn tới file
        Path filePath = Paths.get(UPLOAD_DIR, filePathStr);
        File file = filePath.toFile();

        // Kiểm tra xem file có tồn tại không
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Kiểm tra quyền truy cập file
        if (!file.canRead()) {
            return ResponseEntity.status(403).body(null); // Forbidden
        }

        // Tạo InputStreamResource từ FileInputStream
        InputStreamResource resource;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo InputStreamResource: " + e.getMessage());
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(file.length())
                .body(resource);
    }
}
