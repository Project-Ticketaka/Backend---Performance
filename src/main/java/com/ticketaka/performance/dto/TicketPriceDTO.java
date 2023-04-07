package com.ticketaka.performance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketPriceDTO {
    private String seatType;
    private int price;

    public List<TicketPriceDTO> from(String prfTicketPrice) {
        if(prfTicketPrice == null) {
            return null;
        }
        String[] seats = prfTicketPrice.split(", ");
        List<TicketPriceDTO> ticketPriceDTOS = new ArrayList<>();
        Arrays.stream(seats).forEach(seat -> {
            String pattern = "\\d+,\\d+원|\\d+원";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(seat);
            if (m.find()) {
                String tmp = m.group(1);
                String seatName = seat.replace(tmp, "").trim();
                int price = Integer.parseInt(tmp.replaceAll("[^0-9]",""));
                ticketPriceDTOS.add(new TicketPriceDTO(seatName,price));
            } else {
                ticketPriceDTOS.add(new TicketPriceDTO(seat,0));
            }
        });

        return ticketPriceDTOS;
    }
}
