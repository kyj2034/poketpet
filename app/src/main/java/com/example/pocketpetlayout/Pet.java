package com.example.pocketpetlayout;

public class Pet {
    public static final String TABLE_NAME = "pet";
    public static final String PET_NAME = "pet_name";
    public static final String BIRTHDAY = "birthday";
    public static final String SPECIES = "species";
    public static final String SEX = "sex";
    public static final String OWNER = "owner";

    private static final String SQL_CREATE_PET =
            "CREATE TABLE " + Pet.TABLE_NAME + " (" +
                    Pet.PET_NAME + " TEXT PRIMARY KEY," +
                    Pet.BIRTHDAY + " INTEGER," +
                    Pet.SEX + " TEXT)";


    private static final String SQL_DELETE_PET =
            "DROP TABLE IF EXISTS " + Pet.TABLE_NAME;
}
