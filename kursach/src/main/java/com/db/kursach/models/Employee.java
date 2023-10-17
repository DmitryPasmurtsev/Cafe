package com.db.kursach.models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.buf.Ascii;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "employee_full_name")
    private String fullName;
    @Column(name = "work_experience")
    private int experience;
    @Column(name = "employee_phone_number")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name = "salary")
    private Integer salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "employment_date")
    private LocalDate date;
    @Lob
    @JsonIgnore
    private byte[] image_bytes;
//    @JsonProperty("image_bytes")
////    public String getPhotoBase64() throws SQLException {
////        // just assuming it is a jpeg. you would need to cater for different media types
////        if(image_bytes == null) return null;
////        return Base64.getEncoder().encodeToString(image_bytes);
////    }
//    public String compressString()
//            throws IOException {
//        if(image_bytes == null) return null;
//        ByteArrayOutputStream rstBao = new ByteArrayOutputStream();
//        GZIPOutputStream zos = new GZIPOutputStream(rstBao);
//        zos.write(image_bytes);
//        IOUtils.closeQuietly(zos);
//
//        byte[] bytes = rstBao.toByteArray();
//        // In my solr project, I use org.apache.solr.co mmon.util.Base64.
//        // return = org.apache.solr.common.util.Base64.byteArrayToBase64(bytes, 0,
//        // bytes.length);
//        return Base64.getEncoder().encodeToString(bytes);
//    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "waiter")
    @JsonIgnore
    private List<Order> orders;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
    @JsonIgnore
    private List<Delivery> deliveries;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position1;

//    @OneToOne(mappedBy = "employee")
//    @JsonIgnore
//    private User user;
    @JsonIgnore
    public String toStringSalary(){return salary.toString();}


}
