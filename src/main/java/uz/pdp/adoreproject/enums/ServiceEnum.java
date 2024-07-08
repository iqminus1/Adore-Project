package uz.pdp.adoreproject.enums;

import lombok.Getter;

@Getter
public enum ServiceEnum {
    COMFORTABLE_LOCATION("\uD83D\uDDFA\uFE0F Comfortable location") ,
    ROOM_FOR_COFFEE_BREAK("☕\uFE0F Room for coffee break"),
    FREE_PARKING_ZONE("\uD83C\uDD7F\uFE0F Free parking zone"),
    ORGANIZERS("\uD83D\uDD74\uFE0F Organizers"),
    SINGERS("\uD83C\uDFA4 Singers"),
    HIGH_QUALITY_SERVICE("\uD83C\uDF7D\uFE0F High quality service"),
    FREE_WI_FI("\uD83C\uDF10 Free Wi-Fi"),
    DANCERS("\uD83D\uDC83\uD83D\uDD7A Dancers");
    private final String description;

    ServiceEnum(String description) {
        this.description = description;
    }

}
