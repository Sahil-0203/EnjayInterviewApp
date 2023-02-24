package com.example.enjayinterviewapp

object Constants {

    fun getQuestions(): ArrayList<QuestionModel>{
        val questionList=ArrayList<QuestionModel>()

        val que1 =QuestionModel(
            1,
            "How many numbers between 1 and 100 are divisible by 7?",
            "9",
            "11",
            "17",
            "14",
            4)

        questionList.add(que1)
//        que 2
        val que2 =QuestionModel(
            2,
            "The average age of a man and his son is 28 years. The ratio of their ages is 3 :1 respectively. What is the man's age?",
            "30 years",
            "38 years",
            "42 years",
            "44 years",
            3)

        questionList.add(que2)
//        que 3
        val que3 =QuestionModel(
            3,
            "A train running at the speed of 60 km/hr crosses a pole in 9 seconds. What is the length of the train?",
            "120 metres",
            "180 metres",
            "324 metres",
            "150 metres",
            4)

        questionList.add(que3)
//        que4
        val que4 =QuestionModel(
            4,
            "The cost price of 20 articles is the same as the selling price of x articles. If the profit is 25%, then the value of x is:",
            "15",
            "16",
            "18",
            "25",
            2)

        questionList.add(que4)
//        que5
        val que5 =QuestionModel(
            5,
            "In the first 10 overs of a cricket game, the run rate was only 3.2. What should be the run rate in the remaining 40 overs to reach the target of 282 runs?",
            "6.25",
            "6.5",
            "6.75",
            "7",
            1)
        questionList.add(que5)
//        que6
        val que6 =QuestionModel(
            6,
            "The average weight of 8 person's increases by 2.5 kg when a new person comes in place of one of them weighing 65 kg. What might be the weight of the new person?",
            "76 kg",
            "76.5 kg",
            "85 kg",
            "None of these",
            3)
        questionList.add(que6)
//        que7
        val que7 =QuestionModel(
            7,
            "It was Sunday on Jan 1, 2006. What was the day of the week Jan 1, 2010?",
            "Sunday",
            "Saturday",
            "Friday",
            "Wednesday",
            3)
        questionList.add(que7)
//        que8
        val que8 =QuestionModel(
            8,
            "Today is Monday. After 61 days, it will be:",
            "Wednesday",
            "Saturday",
            "Tuesday",
            "Thursday",
            2)
        questionList.add(que8)
//        que9
        val que9 =QuestionModel(
            9,
            "If a - b = 3 and a2 + b2 = 29, find the value of ab.",
            "10",
            "12",
            "15",
            "18",
            1)
        questionList.add(que9)
//        que10
        val que10 =QuestionModel(
            10,
            "The price of 2 sarees and 4 shirts is Rs. 1600. With the same money one can buy 1 saree and 6 shirts. If one wants to buy 12 shirts, how much shall he have to pay ?",
            "Rs. 1200",
            "Rs. 2400",
            "Rs. 4800",
            "None of these",
            2)

        questionList.add(que10)
//        que11
        val que11 =QuestionModel(
            11,
            "39 persons can repair a road in 12 days, working 5 hours a day. In how many days will 30 persons, working 6 hours a day, complete the work?",
            "10",
            "13",
            "14",
            "15",
            2)
        questionList.add(que11)
//        que12
        val que12 =QuestionModel(
            12,
            "If 7 spiders make 7 webs in 7 days, then 1 spider will make 1 web in how many days?",
            "1",
            "29",
            "7",
            "49",
            3)
        questionList.add(que12)
//        que13
        val que13 =QuestionModel(
            13,
            "A man purchased a cow for Rs. 3000 and sold it the same day for Rs. 3600, allowing the buyer a credit of 2 years. If the rate of interest be 10% per annum, then the man has a gain of:",
            "0%",
            "5%",
            "7.5%",
            "10%",
            1)
        questionList.add(que13)
//        que14
        val que14 =QuestionModel(
            14,
            "A person crosses a 600 m long street in 5 minutes. What is his speed in km per hour?",
            "3.6",
            "10",
            "8.4",
            "7.2",
            4)
        questionList.add(que14)
//        que15
        val que15 =QuestionModel(
            15,
            "A, B and C can do a piece of work in 20, 30 and 60 days respectively. In how many days can A do the work if he is assisted by B and C on every third day?",
            "15 days",
            "12 days",
            "14 days",
            "13 days",
            1)
        questionList.add(que15)
//        que16
        val que16 =QuestionModel(
            16,
            "A can do a piece of work in 4 hours; B and C together can do it in 3 hours, while A and C together can do it in 2 hours. How long will B alone take to do it?",
            "8 hours",
            "10 hours",
            "12 hours",
            "24 hours",
            3)
        questionList.add(que16)
//        que17
        val que17 =QuestionModel(
            17,
            "A father said to his son, \"I was as old as you are at the present at the time of your birth\". If the father's age is 38 years now, the son's age five years back was:",
            "14 years",
            "19 years",
            "33 years",
            "38 years",
            1)
        questionList.add(que17)
//        18
        val que18 =QuestionModel(
            18,
            "The sum of the present ages of a father and his son is 60 years. Six years ago, father's age was five times the age of the son. After 6 years, son's age will be:",
            "12 years",
            "14 years",
            "18 years",
            "20 years",
            4)
        questionList.add(que18)
//        19
        val que19 =QuestionModel(
            19,
            "Sachin is younger than Rahul by 7 years. If their ages are in the respective ratio of 7 : 9, how old is Sachin?",
            "16 years",
            "18 years",
            "24.5 years",
            "28 years",
            3)
        questionList.add(que19)
//        20
        val que20 =QuestionModel(
            20,
            "A bag contains 2 red,3 green and 2 blue balls. Two balls are drawn at random. What is the probability that none of the balls drawn is blue?",
            "10/21",
            "11/21",
            "2/7",
            "5/7",
            1)
        questionList.add(que20)


        return questionList
    }
}