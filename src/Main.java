import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {

    // Define API key and API URL
    private static final String API_KEY = "c42df8ce04mshcf0ab7e5bb17793p14b6f0jsn767fa4a8f8f1";
    private static final String API_URL = "https://love-calculator.p.rapidapi.com/getPercentage";

    // Method to retrieve love percentage between two names
    public static String getLovePercentage(String firstName, String secondName) throws IOException {
        // Encode the names for URL
        String encodedFirstName = URLEncoder.encode(firstName, "UTF-8");
        String encodedSecondName = URLEncoder.encode(secondName, "UTF-8");

        // Construct the URL with encoded names
        String url = String.format("%s?fname=%s&sname=%s", API_URL, encodedFirstName, encodedSecondName);

        // Open a connection to the API URL
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-RapidAPI-Key", API_KEY);

        // Read the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        // Close the connection
        in.close();
        connection.disconnect();

        // Return the response as a string
        return response.toString();
    }

    public static void main(String[] args) {
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their name
        System.out.print("Enter your name: ");
        String firstName = scanner.nextLine();

        // Prompt the user to enter their partner's name
        System.out.print("Enter your partner's name: ");
        String secondName = scanner.nextLine();

        // Close the scanner
        scanner.close();

        try {
            // Get the love percentage using the provided names
            String lovePercentage = getLovePercentage(firstName, secondName);

            // Print the love percentage
            System.out.println("Love Calculator for " + firstName + " and " + secondName + ": ");
            System.out.println(lovePercentage);
        } catch (IOException e) {
            // Print stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
