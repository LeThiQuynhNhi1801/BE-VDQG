package com.example.football.service.Impl;

import com.example.football.dto.Request.EventRequest;
import com.example.football.dto.Response.*;
import com.example.football.entity.Event;
import com.example.football.entity.EventMatch;
import com.example.football.entity.Game;
import com.example.football.entity.Team;
import com.example.football.repo.*;
import com.example.football.service.EventMatchService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventMatchServiceImpl implements EventMatchService {

    private final EventMatchRepository eventMatchRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final LineupRepository lineupRepository;
    private final EventRepository eventRepository;
    private final TeamLeagueRepository teamLeagueRepository;

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<EMResponse> getListEvent(Integer idMatch) {
        Game game = gameRepository.getById(idMatch);
        Integer idHomeTeam = game.getIdHometeam().getIdTeam();
        Integer idAwayTeam = game.getIdAwayteam().getIdTeam();
        List<EventMatch> eventMatchList = eventMatchRepository.getDetail(idMatch);
        String doinha = game.getIdHometeam().getName();
        String doikhach = game.getIdAwayteam().getName();
        List<EMResponse> list = new ArrayList<>();
        List<String> ghiban = new ArrayList<>();
        List<String> phanluoinha = new ArrayList<>();
        List<String> thevang = new ArrayList<>();
        List<String> thedo = new ArrayList<>();
        List<String> vaosan = new ArrayList<>();
        List<String> rasan = new ArrayList<>();
        Integer loi = 0;
        Integer phatgoc = 0;

        List<String> ghiban1 = new ArrayList<>();
        List<String> phanluoinha1 = new ArrayList<>();
        List<String> thevang1 = new ArrayList<>();
        List<String> thedo1 = new ArrayList<>();
        List<String> vaosan1 = new ArrayList<>();
        List<String> rasan1 = new ArrayList<>();
        Integer loi1 = 0;
        Integer phatgoc1 = 0;
        for (EventMatch eventMatch:eventMatchList){
            if(eventMatch.getLineup().getIdTeam()==idHomeTeam){
                if(eventMatch.getEvent().getIdEvent()==1) ghiban.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==2) phanluoinha.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==4) thevang.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==5) thedo.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==6) vaosan.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==7) rasan.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==3) loi+=1;
                if(eventMatch.getEvent().getIdEvent()==8) phatgoc+=1;
            }else {
                if(eventMatch.getEvent().getIdEvent()==1) ghiban1.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==2) phanluoinha1.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==4) thevang1.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==5) thedo1.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==6) vaosan1.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==7) rasan1.add(eventMatch.getLineup().getPlayer().getName());
                if(eventMatch.getEvent().getIdEvent()==3) loi1+=1;
                if(eventMatch.getEvent().getIdEvent()==8) phatgoc1+=1;
            }
        }



        EMResponse em = new EMResponse(doinha,ghiban,phanluoinha,thevang,thedo,loi,phatgoc,rasan,vaosan);
        list.add(em);
        EMResponse em1 = new EMResponse(doikhach,ghiban1,phanluoinha1,thevang1,thedo1,loi1,phatgoc1,rasan1,vaosan1);
        list.add(em1);

        return list;

    }

    @Override
    public List<RankingResponse> getBXHFairplay(Integer idLeague) {
        List<Team> teams = teamLeagueRepository.teamleague(idLeague);
        List<RankingResponse> list = new ArrayList<>();
        for(Team team:teams){
            Integer thevang = eventMatchRepository.getyellowcard(team.getIdTeam());
            Integer thedo = eventMatchRepository.getredcard(team.getIdTeam());
            RankingResponse a = new RankingResponse(team.getName(),team.getIdTeam(),thevang,thedo);
            list.add(a);
        }
        Collections.sort(list, new Comparator<RankingResponse>() {
            @Override
            public int compare(RankingResponse t1, RankingResponse t2) {
                return Integer.compare(t1.getThedo()*3 + t1.getThevang(), t2.getThedo()*3 + t2.getThevang());
            }
        });

        return list;
    }

    @Override
    public List<ematchResponse> getBXHTeam1(Integer idLeague) {
        List<Team> teams = teamLeagueRepository.teamleague(idLeague);
        List<ematchResponse> ematchResponseList = new ArrayList<>();
        for(Team team:teams){
            Integer win = 0;
            Integer drawn =0;
            Integer loss = 0;
            Long hieuso = 0L;
            List<Integer> listMatch = lineupRepository.getMatch(team.getIdTeam(),idLeague);
            for(int i=0;i<listMatch.size();i++){
                Integer opponentTeam;
                Game game = gameRepository.findById(listMatch.get(i)).get();
                if(team.getIdTeam()==game.getIdHometeam().getIdTeam()){
                    opponentTeam = game.getIdAwayteam().getIdTeam();
                }else {
                    opponentTeam = game.getIdHometeam().getIdTeam();
                }
                hieuso += getresult(team.getIdTeam(),opponentTeam,game.getIdMatch());
                if(getresult(team.getIdTeam(),opponentTeam,game.getIdMatch())>0){
                    win+=1;
                }else if(getresult(team.getIdTeam(),opponentTeam,game.getIdMatch())==0){
                    drawn+=1;
                }else loss+=1;
            }
            ematchResponse a = new ematchResponse(team.getName(),team.getIdTeam(),win,loss,drawn,hieuso);
            ematchResponseList.add(a);
        }
        Collections.sort(ematchResponseList, new Comparator<ematchResponse>() {
            @Override
            public int compare(ematchResponse t1, ematchResponse t2) {
                int pointComparison = Integer.compare(t2.getWin()*3+t2.getDrawn(),t1.getWin()*3+t1.getDrawn());
                if (pointComparison == 0) {
                    // Nếu điểm bằng nhau, so sánh theo hiệu số
                    return Long.compare(t2.getHieuso(), t1.getHieuso());
                }
                return pointComparison;
            }
        });
        return ematchResponseList;
    }

    public Long getresult(Integer idTeam,Integer opponentTeam, Integer idMatch) {
        String query = "SELECT" +
                "  SUM(CASE WHEN em.event.id = 1 AND em.lineup.idTeam = :idTeam THEN 1 ELSE 0 END) -" +
                "  SUM(CASE WHEN em.event.id = 2 AND em.lineup.idTeam = :idTeam THEN 1 ELSE 0 END) -SUM(CASE WHEN em.event.id = 1 AND em.lineup.idTeam = :opponentTeam THEN 1 ELSE 0 END) + SUM(CASE WHEN em.event.id = 2 AND em.lineup.idTeam = :opponentTeam THEN 1 ELSE 0 END) as score_team " +
                " FROM Event as e" +
                " JOIN EventMatch as em ON e.id = em.event.id " +
                "WHERE em.lineup.game.id = :idMatch " +
                "GROUP BY em.lineup.game.idMatch";
        Long result =(Long) entityManager.createQuery(query)
                .setParameter("idTeam", idTeam)
                .setParameter("opponentTeam", opponentTeam)
                .setParameter("idMatch", idMatch)
                        .getSingleResult();
        return result;
    }

    @Override
    public List<MatchRespone> getHistoryTeam(Integer idTeam,Integer idLeague) {
        List<MatchRespone> matchResponeList = new ArrayList<>();
        Team team = teamRepository.getById(idTeam);
        List<Integer> listMatch = lineupRepository.getMatch(team.getIdTeam(),idLeague);
        for (int i=0;i<listMatch.size();i++) {
            Game game = gameRepository.getById(listMatch.get(i));
            Integer idHomeTeam = game.getIdHometeam().getIdTeam();
            Integer idAwayTeam = game.getIdAwayteam().getIdTeam();
            String doinha = game.getIdHometeam().getName();
            Integer sobanthang = 0;
            Integer sophanluoinha = 0;
            Integer sobanthang1 = 0;
            Integer sophanluoinha1 = 0;
            List<EventMatch> eventMatchList = eventMatchRepository.getevent(idHomeTeam,game.getIdMatch());
            for(EventMatch eventMatch:eventMatchList){
                if(eventMatch.getEvent().getIdEvent()==1) sobanthang +=1;
                if(eventMatch.getEvent().getIdEvent()==2) sophanluoinha += 1;
            }
            List<EventMatch> eventMatchList1 = eventMatchRepository.getevent(idAwayTeam,game.getIdMatch());
            for(EventMatch eventMatch:eventMatchList1){
                if(eventMatch.getEvent().getIdEvent()==1) sobanthang1 +=1;
                if(eventMatch.getEvent().getIdEvent()==2) sophanluoinha1 += 1;
            }
            String doikhach = game.getIdAwayteam().getName();

            MatchRespone a = new MatchRespone(listMatch.get(i),doinha,doikhach,sobanthang+sophanluoinha1,sobanthang1+sophanluoinha);
            matchResponeList.add(a);
        }
        return matchResponeList;
    }

    @Override
    public void addEvent(EventRequest eventRequest) {
        EventMatch event = new EventMatch(null,eventRepository.getById(eventRequest.getIdEvent()),lineupRepository.getById(eventRequest.getIdLineup()),eventRequest.getDescription(),eventRequest.getTimeEvent());
        eventMatchRepository.save(event);
    }

    @Override
    public List<GoalResponse> BXHGoals() {
//        String query = "select em.lineup.player.name,em.lineup.player.team.name, count (*) as goals from EventMatch em where em.event.idEvent = 1 group by em.lineup.player order by (-goals) limit 10";
//        List<Object> result = entityManager.createQuery(query)
//                .getResultList();
//        List<GoalResponse> goalResponses = new ArrayList<>();
//        for (int i=0;i<result.size();i++){
//            GoalResponse goalResponse = new GoalResponse("1","1",2);
//            goalResponses.add(goalResponse);
//        }
//        return goalResponses;
        String query = "SELECT em.lineup.player.name, em.lineup.player.team.name, COUNT(em) as goals " +
                "FROM EventMatch em " +
                "WHERE em.event.idEvent = 1 " +
                "GROUP BY em.lineup.player " +
                "ORDER BY goals DESC";

        List<Object[]> result = entityManager.createQuery(query)
                .setMaxResults(10)
                .getResultList();

        List<GoalResponse> goalResponses = new ArrayList<>();
        for (Object[] row : result) {
            String playerName = (String) row[0];
            String teamName = (String) row[1];
            Long goals = (Long) row[2];  // COUNT() trả về kiểu Long trong JPQL

            GoalResponse goalResponse = new GoalResponse(playerName, teamName, goals.intValue());
            goalResponses.add(goalResponse);
        }

        return goalResponses;
    }
}

