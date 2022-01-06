package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final int MAX_CANDIDATES = 10; //As the maximum number of candidates are 10, this is a final integer
        String[] nameArray = new String[MAX_CANDIDATES];

        inputNameArray(nameArray); //Fills in the names array with all the required information

        Candidate[] candidateArray = new Candidate[MAX_CANDIDATES];
        inputCandidateArray(candidateArray, nameArray); //Fills in the candidateArray with all the required information

        printAndVoteCandidatesNames(candidateArray); //Prints the candidates names and then allows the user to vote

        assignPositions(candidateArray); //Assigns the positions so that we can find the biggest positions

        int biggestPosition = findBiggestPosition(candidateArray); //Finds the biggest position of all the numbers

        printWinners(candidateArray, biggestPosition); //Prints the final winners of the candidates



    }

    public static void printWinners(Candidate[] candidateArray, int biggestPosition){
        System.out.print("Winner(s) ");
        for (int i = 0; i < candidateArray.length; i++) {
            if (getPosition(candidateArray[i]) == biggestPosition){
                System.out.print(getCandidateName(candidateArray[i]) + " ");
            }
        }
    } //Prints who the final winners with the biggest positions are


    public static int findBiggestPosition(Candidate[] candidateArray){
        int biggestPosition = getPosition(candidateArray[0]);

        for (int i = 1; i < candidateArray.length; i++) {
            if (getPosition(candidateArray[i]) > biggestPosition){
                biggestPosition = candidateArray[i].position;
            }
        }

        return biggestPosition;
    } //Finds the number which is at the highest position, so with the most votes


    public static void assignPositions(Candidate[] candidateArray){
        for (int i = 0; i < candidateArray.length; i++) {
            for (int j = 0; j < candidateArray.length; j++) {
                if (getNumVotes(candidateArray[j]) > getNumVotes(candidateArray[i])){
                    candidateArray[j].position++;
                }
            }
        }
    } //Assign positions to see who has the highest number of votes

    public static void printAndVoteCandidatesNames(Candidate[] candidateArray){

        printCandidatesNames(candidateArray);
        voteForCandidates(candidateArray);
    } //Uses the two methods to print all the candidates names and allows the user to vote for their favourite candidates

    public static void printCandidatesNames(Candidate[] candidateArray){
        System.out.print("Voting: ");
        for (int i = 0; i < candidateArray.length; i++) {
            System.out.print(getCandidateNumber(candidateArray[i]) + "="  + getCandidateName(candidateArray[i]) + "  ");
        }

        print("\n");
    }
    public static void voteForCandidates(Candidate[] candidateArray){
        for (int i = 0; i < candidateArray.length; i++) {
            int vote = inputInt(getCandidateName(candidateArray[i]) + " who do you vote for?");
            vote = checkVote(vote);
            addVote(candidateArray, vote);
        }
    } //Asks the user who they vote for

    public static void addVote(Candidate[] candidateArray, int vote){
        for (int i = 0; i < candidateArray.length; i++) {
            if (vote == getCandidateNumber(candidateArray[i])){
                candidateArray[i].numVotes++;
            }
        }
    } //Adds on votes to each candidate

    public static int checkVote(int vote){
        while (vote > 10 || vote < 1){
            vote = inputInt("Choose from 1 to 10");
        }
        return vote;
    } //Checks that votes are between 1 and 10
    public static void inputCandidateArray(Candidate[] candidateArray, String[] nameArray){
        int i = 0;

        while (i < candidateArray.length){
            candidateArray[i] = createCandidate(nameArray[i] , (i+1));
            i++;
        }
    }//Fills in the candidate array with all the required information

    public static Candidate createCandidate(String name, int number){
        Candidate c = new Candidate();
        c.number = number;
        c.name = name;
        c.numVotes = 0;
        c.position = 0;

        return c;
    } //Creates a new candidate
    public static void inputNameArray(String[] nameArray){
        for (int i = 0; i < nameArray.length; i++) {
            nameArray[i] = inputString("Name " + (i+1));
        }
    } //Fills the string array with all the candidates names


    public static void print(String m) {
        System.out.println(m);
    } //Prints a message

    public static String inputString(String m) {
        String answer;
        Scanner scanner = new Scanner(System.in);

        print(m);
        answer = scanner.nextLine();
        return answer;
    } //Returns a string input

    public static int inputInt(String m) {
        int answer;
        Scanner scanner = new Scanner(System.in);

        print(m);
        answer = scanner.nextInt();
        return answer;
    } //Returns an integer input


    //Accessor method to get all the fields
    public static int getCandidateNumber(Candidate c){
        return c.number;
    }

    public static String getCandidateName(Candidate c){
        return c.name;
    }

    public static int getNumVotes(Candidate c){
        return c.numVotes;
    }

    public static int getPosition(Candidate c){
        return c.position;
    }
}

class Candidate{
    int number;
    String name;
    int numVotes;
    int position;
}
