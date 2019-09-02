package com.brave.service;

import com.brave.model.Fire;
import com.brave.model.Room;
import com.brave.model.Sprinkler;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class FireService {
    @Autowired KieContainer kieContainer;

   String[] names = new String[]{"kitchen","bedroom","office","legroom"};

   private static final Map<String, Room> name2room = new HashMap<>();

   public void init() {
       Arrays.stream(names).forEach(name -> {
           name2room.put(name,new Room(name));
       });
   }

    public void turnOnSprinkler() {
        KieSession kieSession = kieContainer.newKieSession("rulesSession1");
       name2room.forEach((name,room) ->{
           kieSession.insert(room);
           Sprinkler sprinkler = new Sprinkler(room,false);
           kieSession.insert(sprinkler);
       });
        Fire fire = new Fire(name2room.get("office"));
        kieSession.insert(fire);
       kieSession.fireAllRules();
       kieSession.dispose();
    }

}
