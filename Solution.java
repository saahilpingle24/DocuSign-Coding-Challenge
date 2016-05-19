import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        /*
         * Create an object of the helper class DataInjestionHelper for handling data loading activities.
         */
        DataIngestionHelper ingestor = null;

        /*
         * Create a HashMap for storing weather wise clothing data.
         */
        Map<String, HashMap<String, String>> dataStore = new HashMap<String, HashMap<String, String>>();

        /*
         * Setup the weather var and pass it to the helper class for performing data injestion.
         */
        String weather = "hot";
        ingestor.defineInputData(weather, dataStore);

        weather = "cold";
        ingestor.defineInputData(weather,dataStore);

        System.out.println("Enter the input data:");
        String[] input = in.nextLine().split(" ");

        /*
         * Create the sample input to the run the program against.
         */
        if(input.length > 1) {
            List<String> inputData = new ArrayList<>();
            inputData.add(input[0]);
            for(String x : input[1].split(",")) {
                inputData.add(x);
            }
            String finalResponse = checkValidity(inputData, dataStore);
            System.out.print(finalResponse);
        } else {
            System.out.print("fail");
        }
    }

    /*
     * Method for verifying if the current input data is valid or not.
     */
    protected static String checkValidity(List<String> inputData, Map<String, HashMap<String, String>> dataStore) {
        /*
         * Store the final output
         */
        StringBuilder finalResponse = new StringBuilder();

        /*
         * Create a list to make sure that a certain state was already encountered before processing the current state.
         */
        List<Integer> seenState = new ArrayList<>();

        /*
         * Create a HashMap to fetch the data for a given weather input from the dataStore.
         */
        Map<String, String> checker = null;

        /*
         * Capture the weather from the input data and store the corresponding data in the checker map.
         */
        String weather = inputData.get(0);
        checker = dataStore.get(weather.toLowerCase());

        String previousState;

        /*
         * Check for the initial state. If 8, continue else failure case encountered, exit the program flow.
         */
        if(!inputData.get(1).equals("8")) {
            return returnError(finalResponse);
        } else {
            /*
             * Keep track of the previous state and add the current state to he list of seen states.
             */
            previousState = inputData.get(1);
            seenState.add(8);
            finalResponse.append(checker.get(inputData.get(1))+", ");
        }

        /*
         * Iterate over rest of the input data and check if it meets system requirements.
         */
        for(int i=2; i<inputData.size(); i++) {
            if(previousState.equals(inputData.get(i))) {
                return returnError(finalResponse);
            } else {
                /*
                 * Perform check to make sure a particular state has not occurred before the current state has been processed.
                 */
                if(inputData.get(i).equals("3") && seenState.contains("1")) {
                    return returnError(finalResponse);
                }
                if(inputData.get(i).equals("6") && seenState.contains("1")) {
                    return returnError(finalResponse);
                }
                if(inputData.get(i).equals("4") && (seenState.contains("2") || seenState.contains("5"))) {
                    return returnError(finalResponse);
                }

                /*
                 * Perform check to check if the data store has a fail condition for any particular state, In that case, halt and return fail state.
                 */
                if(checker.get(inputData.get(i)).equals("fail")) {
                    return returnError(finalResponse);
                }

                /*
                 * Perform check to make sure that before the final state has been processed, all the necessary system requirements have been met.
                 */
                if(inputData.get(i).equals("7"))  {
                    if(!isValidExit(seenState, weather)) {
                        return returnError(finalResponse);
                    }
                }

                /*
                 * If no errors were encountered, set the values for:
                 * 1. previous state.
                 * 2. add the current state to the list of seen states.
                 * 3. display the o/p for the current state.
                 */
                previousState = inputData.get(i);
                checker.get(inputData.get(i));
                seenState.add(Integer.parseInt(inputData.get(i)));
                finalResponse.append(checker.get(inputData.get(i))+", ");
            }
        }
        String returnValue = finalResponse.toString();
        return returnValue.substring(0,returnValue.length()-2);
    }

    /*
     * Logic for making sure that all system requirements have been met before processing the final state.
     */
    protected static boolean isValidExit(List<Integer> seenState, String weather) {
        if(weather.equalsIgnoreCase("cold")) {
            for(int j=1; j<7; j++) {
                if(!seenState.contains(j)) return false;
            }
        } else if(weather.equalsIgnoreCase("hot")) {
            for(int j=1; j<7; j++) {
                if(!seenState.contains(j)) {
                    if(j==3 || j==5) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*
     * Construct the error response and return.
     */
    protected static String returnError(StringBuilder finalResponse) {
        finalResponse.append("fail");
        return finalResponse.toString();
    }

}
