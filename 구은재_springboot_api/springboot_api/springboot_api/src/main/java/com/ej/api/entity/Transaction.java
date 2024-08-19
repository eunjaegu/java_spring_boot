package com.ej.api.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "transactions")
@Data  // 롬복의 @Data 어노테이션은 게터, 세터, toString, hashCode, equals 메소드를 자동으로 생성합니다.
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor  // 모든 필드를 파라미터로 하는 생성자
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date transactionDate; //이용일시

    @Column
    private Long approvalNumber;

    @Column
    private String accountType;

    @Column
    private String brand;

    @Column
    private String cardNumber;

    @Column
    private String merchantName; //가맹점명

    @Column
    private Long amount; // 이용금액

    @Column
    private String transactionType;

    @Column
    private String acquisitionStatus;

}
