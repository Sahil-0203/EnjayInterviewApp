package com.example.enjayinterviewapp

object LogicalConstants {

    fun getQuestion():ArrayList<QuestionModel>{
        val questionList=ArrayList<QuestionModel>()

        val que1 =QuestionModel(
            1,
            "Look at this series: 7, 10, 8, 11, 9, 12, ... What number should come next?",
            "7",
            "10",
            "17",
            "14",
            2)

        questionList.add(que1)

//        que 2
        val que2 =QuestionModel(
            2,
            "Look at this series: 53, 53, 40, 40, 27, 27, ... What number should come next?",
            "12",
            "34",
            "27",
            "14",
            4)

        questionList.add(que2)

//        que 3
        val que3 =QuestionModel(
            3,
            "Look at this series: 3, 4, 7, 8, 11, 12, ... What number should come next?",
            "15",
            "18",
            "14",
            "13",
            1)

        questionList.add(que3)
//        que4
        val que4 =QuestionModel(
            4,
            "Tanya is older than Eric,Cliff is older than Tanya,Eric is older than Cliff. If the first two statements are true, the third statement is",
            "true",
            "false",
            "uncertain",
            "None Of These",
            2)

        questionList.add(que4)
//        que5
        val que5 =QuestionModel(
            5,
            "Mara runs faster than Gail,Lily runs faster than Mara,Gail runs faster than Lily,If the first two statements are true, the third statement is",
            "uncertain",
            "true",
            "false",
            "None Of These",
            3)
        questionList.add(que5)

//        que6
        val que6 =QuestionModel(
            6,
            "Rover weighs less than Fido,Rover weighs more than Boomer,Of the three dogs, Boomer weighs the least,If the first two statements are true, the third statement is",
            "true",
            "false",
            "uncertain",
            "None Of These",
            1)
        questionList.add(que6)

//        que7
        val que7 =QuestionModel(
            7,
            "Look at this series: 36, 34, 30, 28, 24, … What number should come next?",
            "26",
            "20",
            "22",
            "18",
            3)
        questionList.add(que7)

//        que8
        val que8 =QuestionModel(
            8,
            "Look at this series: 80, 10, 70, 15, 60, … What number should come next?",
            "20",
            "25",
            "55",
            "50",
            1)
        questionList.add(que8)

//        que9
        val que9 =QuestionModel(
            9,
            "Look at this series: 2, 1, (1/2), (1/4), ... What number should come next?",
            "1/3",
            "1/8",
            "2/8",
            "1/16",
            2)
        questionList.add(que9)


//        que10
        val que10 =QuestionModel(
            10,
            "Look at this series: 2, 6, 18, 54, ... What number should come next?",
            "108",
            "148",
            "162",
            "216",
            3)
        questionList.add(que10)
        return questionList
    }
}