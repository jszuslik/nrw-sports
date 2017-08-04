package com.norulesweb.authapp.core.model.usertypes;

public class Enums {

    public enum UserType {
        athlete_type("Athlete"),
        coach_type("Coach"),
        parent_type("Parent");

        private final String text;

        UserType(String text) { this.text = text; }

        @Override
        public String toString() { return text; }

        public static UserType getEnum(String string) {
            for(UserType ut : values()) {
                if(ut.toString().equalsIgnoreCase(string)) return ut;
            }
            throw new IllegalArgumentException();
        }
    }

}
