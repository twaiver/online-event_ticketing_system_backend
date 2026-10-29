package online.event.ticketing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.event.ticketing.domain.Buyer;
import online.event.ticketing.repository.BuyerRepository;

@Service 
public class BuyerService {
    
    @Autowired 
    private BuyerRepository buyerRepository;

    public String saveBuyer(Buyer buyer) {
        boolean check = buyerRepository.existsByEmail(buyer.getEmail());
        if(check) {
            return "buyer with that email " + buyer.getEmail() + " is already available";
        } else {
            buyerRepository.save(buyer);
            return "buyer have been saved succuesfully";
        }
    }

    public List<Buyer> allBuyers() {
        List<Buyer> buyers = buyerRepository.findAll();

        return buyers;
    }

    public String deleteBuyer(UUID id) {
        boolean buy = buyerRepository.existsById(id);
        if (!buy) {
            return "the Buyer with that id is not exist";
        } else {
            buyerRepository.deleteById(id);
            return "the buyer with id of " + id + " deleted successfully";
        }
    }


    public String updateBuyer(UUID id, Buyer buyer) {
        Optional<Buyer> buy = buyerRepository.findById(id);

        if(buy.isEmpty()) {
            return "the buyer with that id is not exist";
        } else {
            System.out.println("the id is " + id);
            Buyer newBuy = buy.get();
            newBuy.setEmail(buyer.getEmail());
            newBuy.setFirstName(buyer.getFirstName());
            newBuy.setLastName(buyer.getLastName());
            newBuy.setPhone(buyer.getPhone());

            buyerRepository.save(newBuy);

            return "the buyer with this id " + id + " have been updated succeffly";
        }
    }

    public List<Buyer> findByLastName(String lastName){
        List<Buyer> result = buyerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
        return result;
    }

    public List<Buyer> allBuyersByOrganizer(UUID id) {   

        List<Buyer> data = buyerRepository.allBuyerByOrganizer(id);

        return data;
    }
}
