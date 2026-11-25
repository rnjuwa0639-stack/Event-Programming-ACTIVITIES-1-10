package com.example;

/**
 * Console-only demo, so it is a plain main() class (no window is opened and
 * the program ends by itself).
 */
public class ListenerDemo {

    public static void main(String[] args) {
        Person person = new Person();

        // 1. InvalidationListener (fires when the property becomes "invalid")
        person.nameProperty().addListener(observable ->
                System.out.println("1. [InvalidationListener] Name was changed!"));

        // 2. ChangeListener (provides old and new values)
        person.nameProperty().addListener((observable, oldValue, newValue) ->
                System.out.println("2. [ChangeListener] Name changed from '"
                        + oldValue + "' to '" + newValue + "'"));

        System.out.println("--- Setting name to Alice ---");
        person.setName("Alice");

        System.out.println("\n--- Setting name to Bob ---");
        person.setName("Bob");

        System.out.println("\n--- Setting name to Bob (same value) ---");
        person.setName("Bob"); // Neither listener fires: the value did not change
    }
}
