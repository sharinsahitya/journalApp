package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journals")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
//        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
//        return journalEntries.get(myId);
        return null;
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteJournalEntry(@PathVariable Long myId){
//        return journalEntries.remove(myId);
        return null;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateJourneyById(@PathVariable Long myId, @RequestBody JournalEntry myEntry){
//        return journalEntries.put(myId,myEntry);
        return null;
    }
}
