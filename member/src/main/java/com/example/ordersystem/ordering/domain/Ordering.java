package com.example.ordersystem.ordering.domain;

import com.example.ordersystem.common.domain.BaseTimeEntity;
import com.example.ordersystem.member.domain.Member;
import com.example.ordersystem.ordering.dtos.OrderDetailDto;
import com.example.ordersystem.ordering.dtos.OrderListResDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class Ordering extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus orderStatus = OrderStatus.ORDERED;

    @OneToMany(mappedBy = "ordering", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public OrderListResDto fromEntity() {
        List<OrderDetailDto> orderDetailResDtos = new ArrayList<>();
        for (OrderDetail od : this.getOrderDetails()) {
            OrderDetailDto orderDetailDto =OrderDetailDto.builder()
                    .detailId(od.getId())
                    .productName(od.getProduct().getName())
                    .count(od.getQuantity())
                    .build();
            orderDetailResDtos.add(orderDetailDto);
        }
        OrderListResDto orderDto = OrderListResDto
                .builder()
                .id(this.getId())
                .memberEmail(this.getMember().getEmail())
                .orderStatus(this.getOrderStatus().toString())
                .orderDetails(orderDetailResDtos)
                .build();
        return orderDto;
    }

    public void orderCancel() {
        this.orderStatus = OrderStatus.CANCELED;
    }
}
