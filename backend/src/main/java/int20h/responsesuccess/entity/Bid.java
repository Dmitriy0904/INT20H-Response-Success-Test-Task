package int20h.responsesuccess.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import int20h.responsesuccess.model.BidRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @Column(name = "created_date")
    private Long createdDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="users_id")
    private User user;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="auction_id")
    private Auction auction;

    public Bid(BidRequestDto dto, User user, Auction auction) {
        this.id = dto.getId();
        this.amount = dto.getAmount();
        this.createdDate = Optional.ofNullable(dto.getCreatedDate()).orElse(System.currentTimeMillis());
        this.user = user;
        this.auction = auction;
    }
}
