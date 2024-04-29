package lk.ijse.hardwareManagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Customerdto {


    private String name;
    private int id;
    private String address;
    private String contact;
    private String Email;
}

