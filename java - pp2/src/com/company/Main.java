package com.company;

import java.util.*;

public class Main {

    private static List<String> userNames,userResults,allSet; // global array list

    public static void main(String[] args) {
        userNames = new ArrayList<>();
        userResults = new ArrayList<>();
        allSet = new ArrayList<>();
        welcome();

    }

    private static void welcome(){
        System.out.println("\n Welcome to Springfield GolfClub. \n Select Options: \n 1) Enter Scores \n 2) Find Golfers \n 3) Display Scoreboard \n 4) Exit Program \n");
        System.out.print("what is your choice? : ");
        Scanner userInput = new Scanner(System.in);
        int choose = -1;
        while (choose < 1 || choose > 4) {
            try {
                choose = userInput.nextInt();
            }
            catch (InputMismatchException ex){
                System.out.println("You entered something wrong it seems, please choose a valid method number from above.");
                System.out.print("what is your choice? : ");
                userInput.next();
            }

        }

        switch (choose) {
            case 1:
                System.out.println("\n 1) Enter player details \n 2) Edit Data \n 3) Delete Data \n 4) Main menu \n 5) Exit \n");
                System.out.print("How do you want to modify? : ");
                Scanner mod = new Scanner(System.in);
                int modify = -1;
                while (modify < 1 || modify > 5) {          // THERE'S AN ERROR WITH THE LOOP
                    try {
                        modify = mod.nextInt();

                    }
                    catch (InputMismatchException ex){
                        System.out.println("You entered something wrong it seems, please choose a valid method number from above.");
                        System.out.print("How do you want to modify? : ");
                        mod.next();
                    }

                }

                switch (modify){
                    case 1:
                        enterDetails();
                    case 2:
                        editDetails();
                    case 3:
                        deleteData();
                    case 4:
                        welcome();
                    case 5:
                        System.out.println("\n ..........Thank you..........");
                        System.exit(0);

                    default:
                        welcome();


                }


            case 2:
                Scanner findUser = new Scanner(System.in);
                System.out.print("Please enter the player's name you want to find : ");
                String findUserInput = findUser.next();
                findUserMethod(userNames,findUserInput);
                welcome();

            case 3:
                viewAll();
                welcome();

            case 4:
                System.out.println("\n ..........Thank you..........");
                System.exit(0);


            default:
                System.out.println("Check again");
                welcome();

        }
    }


    private static void enterDetails(){
        System.out.print("How many golfers do you want to add to your group? : ");
        Scanner num = new Scanner(System.in);
        int numOfPlayers = -1;
        while (numOfPlayers < 0 || numOfPlayers > 100){
            try {
                numOfPlayers = num.nextInt();
            }catch (InputMismatchException ex){
                System.out.println("You entered something wrong it seems, please enter a valid integer...");
                System.out.print("How many golfers are there in your group? : ");
                num.next();

            }
        }
        Scanner name = new Scanner(System.in);
        Scanner result = new Scanner(System.in);
        for (int i=1; i<=numOfPlayers; i++){
            System.out.print("Please enter player "+i+" name : ");
            String nameInput = name.next();
            useLoop(userNames, nameInput);
            userNames.add(nameInput);
            int resultInput = 0;
            while (resultInput < 18 || resultInput > 108){
                try {
                    System.out.print("Please enter player "+i+" result : ");
                    resultInput = result.nextInt();
                    if (resultInput < 18 || resultInput > 108){
                        System.out.println("The result you entered is out of range. [ The result should range between 18 - 108 ]");
                    }

                }catch (InputMismatchException ex){
                    System.out.println("You entered something wrong it seems, please enter a valid result...");
                    result.next();

                }

            }
            userResults.add(String.valueOf(resultInput));

        }


        welcome();
    }

    private static void editDetails(){
        Scanner modify_D = new Scanner(System.in);
        System.out.print("Please enter the player's name you want to edit : ");
        String modifyDetail = modify_D.next();
        checkArray(userNames,modifyDetail);
        welcome();
    }

    private static void findUserMethod(List<String> arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue)) {
                System.out.println("\nPlayer Name : "+ targetValue);
                int index = userNames.indexOf(targetValue);
                System.out.println("Player score : "+ userResults.get(index));
                return;
            }
        }
        System.out.println("Sorry, you might have mistaken, no player exists with that name. Check again.");
        welcome();
    }

    private static void viewAll(){
        System.out.println("\nNAME___SCORE");
        sortedData();
        for (String s: allSet){
            System.out.println(s);
        }
    }

    private static void sortedData(){
        allSet.clear();
        for(int i=0; i<userNames.size(); i++){
            allSet.add(userNames.get(i) +"\t"+ userResults.get(i));
        }
        Set<String> set = new HashSet<>(allSet);
        allSet.clear();
        allSet.addAll(set);
        sort(allSet);

    }

    private static void sort(List<String> arr){
        int j = arr.size()-1;
        String temp1,temp2;
        boolean flag = true;

        while(flag){
            flag=false;

            for(int i = 0 ; i < j ; i++){
                if(Integer.parseInt(arr.get(i).substring(arr.get(i).indexOf("\t")+1)) > Integer.parseInt(arr.get(i+1).substring(arr.get(i+1).indexOf("\t")+1)))
                {
                    temp1 = arr.get(i);
                    temp2 = arr.get(i+1);
                    arr.set(i, temp2);
                    arr.set(i+1, temp1);

                    flag=true;
                }
            }
            j--;
        }
    }

    private static void checkArray(List<String> arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue)) {
                int index = userNames.indexOf(targetValue);
                System.out.print("Please enter the player's new name [if this is not to be changed, please enter the same] : ");
                Scanner newName = new Scanner(System.in);
                String newNameInput = newName.next();
                Scanner newScore = new Scanner(System.in);
                int newScoreInput = 0;
                while (newScoreInput < 18 || newScoreInput > 108){
                    try {
                        System.out.print("Please enter the player's new score : ");
                        newScoreInput = newScore.nextInt();
                        if (newScoreInput < 18 || newScoreInput > 108){
                            System.out.print("The result you entered is out of range. [ The result should range between 18 - 108 ]");
                        }

                    }catch (InputMismatchException ex){
                        System.out.println("You entered something wrong it seems, please enter a valid result...");
                        newScore.next();

                    }

                }
                userNames.set(index,newNameInput);
                userResults.set(index, String.valueOf(newScoreInput));
                return;
            }
        }
        System.out.println("Sorry, you might have mistaken, no player exists with that name. Check again.");
        welcome();
    }

    private static void useLoop(List<String> arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue)) {
                System.out.println("Already there's a player exists with the same name. Please choose an option below to continue : \n 1) I wish to continue [ the existing player will be replaced ]. \n 2) I want to enter another name except. [ the existing player will not be replaced ] ");
                Scanner update = new Scanner(System.in);
                int updateInput = 0;
                while (updateInput < 1 || updateInput > 2) {
                    try {
                        updateInput = update.nextInt();

                    }
                    catch (InputMismatchException ex){
                        System.out.println("You entered something wrong it seems, please choose a valid method number from above.");
                        System.out.print("what is your choice? : ");
                        update.next();
                    }
                }
                switch (updateInput){
                    case 1:
                        editDetails();
                    case 2:
                        enterDetails();
                    default:
                        System.out.println("Check again");
                }
                return;
            }
        }
    }


    private static void deleteData(){
        try {
            System.out.print("Please enter the players name you you want to delete : ");
            Scanner deleteName = new Scanner(System.in);
            String deleteNameInput = deleteName.next();
            int index = userNames.indexOf(deleteNameInput);
            userNames.remove(deleteNameInput);
            userResults.remove(index);
        }catch (Exception e) {
            System.out.println("Sorry, you might have mistaken, no player exists with that name. Check again.");
            welcome();
        }
    }

}
