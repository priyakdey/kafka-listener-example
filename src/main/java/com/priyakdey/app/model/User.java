package com.priyakdey.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Priyak Dey
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private UUID id;
    private String name;
    private String email;
    private Boolean isVerified;

}
