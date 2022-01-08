In this try you will create a class diagram for a quiz program, then create a
grading program matching a different, provided class diagram.

1. Download and open the BlueJ project [QuizCD](https://amazon.awsapps.com/workdocs/index.html#/document/97a0b207fd8d824bd70ac09da8a05100de15bc2e5791b10bb61a2fee9e5a5a19).
This code is the beginning of a program that administers a multiple-choice 
quiz. Make a class diagram for this program using [Amazon's internal PlantUML 
server](https://plantuml.corp.amazon.com/plantuml/form/encoded.html#encoded=SoWkIImgAStDuUBYSaZDIm6n0000).
Save the text in the file Quiz_CD.plantuml.
    1. Verify your results by visually comparing with 
    [the solution class diagram](https://plantuml.corp.amazon.com/plantuml/form/encoded.html#encoded=RP2n3e8m48RtFaN3X1X8J9oSy0Kqni71E5HQIHDeO-yGe_7TFKg149rw_R__Udyl1nGEcxfYhAW403yq-iNVZFEKtni5gAq1lY6vhyyNHcp9XQorqO3AnGl2sg2NIuMxfhugjxyD7J0p22a7uFM7rP9zfk4z2W5GFP4yHtJQb5ufh7EgmAs1LZb2VU5Hwwmf-pkCXEA6gQS9nsBU_fiZlFyl1NdyYg9Ln6sQqfbbIJGcP-CNEaFI6TRU49AoN1b9w_u2). Account for any differences. (A graphical diff tool, such as [Meld](http://meldmerge.org/), is entirely optional but could be useful.)
    
2. Examine [the class diagram for the quiz grading service](https://plantuml.corp.amazon.com/plantuml/form/encoded.html#encoded=ZLJBRXGn4BpxAvpbiacOcGHGW5KKPGa28V6A4ea3uk2rpSII7yGFDWdYtsbxRC_iBaYSnktghgvsjURSUM9zK1Bj2KrbO3nOYM-y_-4MNUUUDi4rQ-vyywGbYZmOJTQkfKPrn9E61ci53P8qYdXk1P4TSOvxrpb_Bxbh1H3ZlUNr4Y4g0S9lBM7Suby8umOx3-sqTtY1huRZrsy076B2bD320UKyvxnZASaAtJ_6jq4yG7W9dttu2krJLS_zU-wXA3NvIsLCdcJaLYptBWMZcfZ_qmY6VXVv6SZwnKYTuZwoncaIVPs53NeIMi1TwdITYNLGbm4UNHZjwe3f-i9OowclbsNmpznI47P5ZUNpICvCM4aUwJcKJSS-FOqtt998VtQMMXxY9TIyF6gFKl0AWXSbk83WUGbUGt1IWZSG3ESaR_huu5-MLBNi5wqVm5iLLCQtrEO5QJReoktiU0PzPiTjUp1BzaFfk2jkccQQcfXDGyMy21-CyD0Xkv187qdHttYWv78bGp7bTk98SGSytbxOhOty6DIAsq_VgvyR2R0S1PZlhaI1_Y78ypk_kO1qs8wbTcjuXWKU3WbQMwFx8NOL6LVqomZFoMRvhgxC_p-ge2OzwPObPJ9KHwocF4i9MJb2vrmp-8dy0G00).
Add implementations for the classes described by the class diagram to the 
QuizCD project. (The Quiz, Question, and Answer classes have already been 
implemented in the QuizCD project.)
    
    It's usually easiest to start with the class that has the fewest 
    dependencies.
    1. Create the `LetterGrade` enumeration.
        1. Add a double for the minimum percent score to achieve the 
        LetterGrade.
        2. Implement the LetterGrade constructor that takes a double for 
        `minPercent`. 
        3. Implement the `getMinimumPercent()` method that returns the 
        minimum percent score to achieve the LetterGrade.
        4. Add all the LetterGrade enumeration values, calling the 
        constructor with the appropriate minimum percentage.
    2. Create the `Grade` class.
        1. Add the `numQuestions` and `numCorrect` attributes. (Neither 
        getters nor setters will be necessary.)
        2. Implement a constructor that sets the `numQuestions` and 
        `numCorrect` attributes.
        3. Implement `getPercentScore()`. Keep in mind that this should 
        return the *percent* score: the number of correct answers / the 
        number of questions * 100. Integer math will not be sufficient to 
        calculate accurate scores; you'll want to cast one of the operands 
        to a double.
    3. Create the `Student` class.
        1. Implement a constructor that takes the student ID. 
        2. Implement a getter for the ID. (No setter will be necessary.)
        3. Add the `grades` attribute. Initialize it to an empty array 
        (`new Grade[0]`).
        4. Implement a getter for the grades. (No setter will be necessary.)
        5. Implement `addGrade()`. This needs to copy the existing grades 
        to a slightly larger array and add the new Grade. There are examples 
        in the provided `Quiz` and `Question` classes.
    4. Create the `Grader` class.
        1. Add the `students` attribute and initialize it to an empty 
        `Student[]`.
        2. Implement `addStudent()`. Like `Student.addGrade()`, this needs to
        expand and copy an array.
        3. Thinking ahead, both `administer()` and `getLetterGrade()` have to
        find a `Student` from a `studentId`. Instead of duplicating code, 
        write a helper method that takes a studentId as its parameter, loops 
        through the `students` until it finds a match, and returns that 
        Student.
        4. Implement `getLetterGrade()`. This will loop through the student's
        grades, calling `getPercentScore()` on each, and calculate the average.
        Because the LetterGrade values are in descending order, it can then 
        simply loop through `LetterGrade.values()` and return the first one 
        that is less than or equal to the student's average.
        5. Implement `administer()`. This will find the correct the Student, 
        then call `quiz.administer()` and `quiz.getNumberOfQuestions()`. 
        Finally, it will create a new Grade using these values and add it to 
        the Student.
3. Verify your results.
    1. Download the [GraderTester.java](https://amazon.awsapps.com/workdocs/index.html#/document/ab2a2b9c6dea762c390f31dd1f6fd240a5fb95db313e2f94bdd8608347319a32) and run it. If you've implemented all the classes and methods as described in the class diagram, nothing should need to be renamed. It will print messages indicating whether your code calculated the grade as expected for two different students.

