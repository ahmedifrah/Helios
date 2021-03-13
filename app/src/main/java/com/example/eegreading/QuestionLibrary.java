package com.example.eegreading;


public class QuestionLibrary {
    public static String mQuestions [] = new String[]{
            "Little interest or pleasure in doing things?",
            "Feeling down, depressed, or hopeless?",
            "Trouble falling or staying asleep, or sleeping too much?",
            "Feeling tired or having little energy?",
            "Poor appetite or overeating?",
            "Feeling bad about yourself - or that you are a failure or have let yourself or your family down?",
            "Trouble concentrating on things, such as reading the newspaper or watching television?",
            "Moving or speaking so slowly that other people could have noticed?\n" +
                    "Or the opposite - being so fidgety or restless that you have been moving around a lot more than usual?",
            "Thoughts that you would be better off dead, or of hurting yourself in some way?"


    };


    private String mChoices [][] = {
            {"Not at all", "Several Days", "More than half the days", "Nearly every day"},
            {"Not at all", "Several Days", "More than half the days", "Nearly every day"},
            {"Not at all", "Several Days", "More than half the days", "Nearly every day"},
            {"Not at all", "Several Days", "More than half the days", "Nearly every day"},
            {"Not at all", "Several Days", "More than half the days", "Nearly every day"},
            {"Not at all", "Several Days", "More than half the days", "Nearly every day"},
            {"Not at all", "Several Days", "More than half the days", "Nearly every day"},
            {"Not at all", "Several Days", "More than half the days", "Nearly every day"},
            {"Not at all", "Several Days", "More than half the days", "Nearly every day"}
    };

//    private String mCorrectAnswers[] = {"Not at all", "Several Days", "More than half the days", "Nearly every day", "Not at all", "Several Days", "More than half the days", "Nearly every day",
//    "Not at all"};






    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getChoice4(int a) {
        String choice3 = mChoices[a][3];
        return choice3;
    }

//    public String getCorrectAnswer(int a){
//        String answer = mCorrectAnswers[a];
//        return answer;
//    }


}


