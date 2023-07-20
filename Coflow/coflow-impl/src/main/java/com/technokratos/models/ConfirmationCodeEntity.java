package com.technokratos.models;

import com.technokratos.dto.enums.CodeType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "confirmation_code")
public class ConfirmationCodeEntity extends ExtendedAbstractEntity {

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    private UUID code;

    @Enumerated(value = EnumType.STRING)
    private CodeType type;
}

