package com.example.user.ebooks.db;

import com.example.user.ebooks.R;
import com.example.user.ebooks.base.Book;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<Book> getPopularBooks(){

        List<Book> lstBooks = new ArrayList<>();
        Book book1= new Book("Letters Of AmericanLady", R.drawable.letterstoamericanlady,R.drawable.letterstoamericanlady,1,"I. LETTER TO AN AMERICAN:\n" +
                "Why are you hated? Why are you loved?\n" +
                "IT’S QUITE POSSIBLE, American, that it has not yet dawned on you that\n" +
                "you are the result of the most peculiar and passionate social affair of all times.\n" +
                "Also born from this affair are all those special characteristics that make you\n" +
                "the most vital and energetic society currently on the planet. Curiously and\n" +
                "simultaneously, you are the most hated and the most loved.");
        book1.setPremium(true);
        lstBooks.add(book1);

        lstBooks.add(new Book("What You Need To Know About Economics To Be Happier",R.drawable.whatuneed,R.drawable.whatuneed,2,"There are two subjects that should be taught to the world’s children before they reach puberty. One is fluency of spoken expression, and the other is economics, as both will be pivotal to their development in their future lives.\n" +
                "A high level of ability in language use opens a lot of doors because it facilitates communication with others, allowing people to express their thoughts, feelings and desires effectively. This in turn facilitates the integration of young people into society. Language unites us. I am sure that just about everyone will agree with this idea.\n" +
                "However, in the case of economics, no doubt there will be less consensus. I suspect that many readers have furrowed their brows, wondering whether to continue reading, because they believe economics is a boring subject for specialists, which ordinary people will never understand.\n" +
                "This impression is false. It is very easy to understand, and it is knowledge that is extremely useful because your whole life, from cradle to grave—and even afterwards—is affected by economics."));

        lstBooks.add(new Book("The Tree",R.drawable.tree,R.drawable.tree,3,"Freeditorial Literary Contest. Thanksgiving Day, 2015\n" +
                "“The Tree” by K.M. Fox, won the 2nd Prize: $ 5.000\n" +
                "\n" +
                "Chapter One: March 14, 2009 Aiken Somerset looked around, blinking away the rain from his eyes. There were red and blue lights flashing through the trees in the direction of the street, and he hoped that no one he knew needed the ambulance. He craned his neck to look at the familiar specimen of fagus grandifolia, smiling at the sight of buds forming along the outermost twigs. The rain may have been close to freezing, but his tree knew that spring was just around the corner. He looked down again, and his smile faded. It had been the first time he’d ever received anything from the hole in the enormous beech tree’s trunk. Before, he had only ever deposited gifts."));

        lstBooks.add(new Book("The Art Of War",R.drawable.war,R.drawable.war,4,"Twenty-Five Hundred years ago, Sun Tzu wrote this classic book of military strategy based on Chinese warfare and military thought. Since that time, all levels of military have used the teaching on Sun Tzu to warfare and cilivzation have adapted these teachings for use in politics, business and everyday life. The Art of War is a book which should be used to gain advantage of opponents in the boardroom and battlefield alike."));


        lstBooks.add(new Book("Poetry Of Art",R.drawable.poetryofart,R.drawable.poetryofart,5,"written poems on different topics"));

        lstBooks.add(new Book("Peter Pan",R.drawable.peterpan,R.drawable.peterpan,6,"Peter Pan is a fictional character created by Scottish novelist and playwright J. M. Barrie. A free-spirited and mischievous young boy who can fly and never grows up, Peter Pan spends his never-ending childhood having adventures on the mythical island of Neverland as the leader of the Lost Boys, interacting with fairies, pirates, mermaids, Native Americans, and occasionally ordinary children from the world outside Neverland."));




        return lstBooks;
    }

    public static List<Book> getLastWeekBook(){

        List<Book> lstBooks = new ArrayList<>();
        lstBooks.add(new Book("The Record",R.drawable.record,R.drawable.record,7,"One\n" +
                "MAN PUT GUARDS on the walls that he had built around cities and now\n" +
                "called them nations. He placed flags atop the highest mountains, and would\n" +
                "kill and die for these flags.\n" +
                "And Authority tore down the walls and abolished the flags.\n" +
                "He saw how men spoke in different languages that divided them.\n" +
                "And so Authority taught one single language to all the people of the Earth."));
            Book book2= new Book("Romeo And Juliet",R.drawable.romeoandjuliet,R.drawable.romeoandjuliet,8,"Two households, both alike in dignity,\n" +
                    "In fair Verona, where we lay our scene,\n" +
                    "From ancient grudge break to new mutiny,\n" +
                    "Where civil blood makes civil hands unclean.\n" +
                    "From forth the fatal loins of these two foes\n" +
                    "A pair of star-cross'd lovers take their life;\n" +
                    "Whole misadventured piteous overthrows\n" +
                    "Do with their death bury their parents' strife.\n" +
                    "The fearful passage of their death-mark'd love,\n" +
                    "And the continuance of their parents' rage,\n" +
                    "Which, but their children's end, nought could remove,\n" +
                    "Is now the two hours' traffic of our stage;\n" +
                    "The which if you with patient ears attend,\n" +
                    "What here shall miss, our toil shall strive to mend.");
            book2.setPremium(true);
        lstBooks.add(book2);

        lstBooks.add(new Book("Little Prince",R.drawable.the_little_prince,R.drawable.the_little_prince,9,"Moral allegory and spiritual autobiography, The Little Prince is the most translated book in the French language. With a timeless charm it tells the story of a little boy who leaves the safety of his own tiny planet to travel the universe, learning the vagaries of adult behaviour through a series of extraordinary encounters. His personal odyssey culminates in a voyage to Earth and further adventures."));

        lstBooks.add(new Book("Dracula",R.drawable.dracula,R.drawable.dracula,10,"Dracula is an 1897 Gothic horror novel by Irish author Bram Stoker. It introduced the character of Count Dracula, and established many conventions of subsequent vampire fantasy.[1] The novel tells the story of Dracula's attempt to move from Transylvania to England so that he may find new blood and spread the undead curse, and of the battle between Dracula and a small group of men and a woman led by Professor Abraham Van Helsing."));

        lstBooks.add(new Book("The Bow",R.drawable.bow,R.drawable.bow,11,"The Freeditorial Prizes. Thanksgiving Day, 2016\n" +
                "Won the 2nd Prize: $ 5.000\n" +
                "Synopsis:\n" +
                "“How long?” she asked in an emotionless tone; the only question left intruding her mind. After all, the easy part had been completed.\n" +
                "“That’s the beauty” the man smirked, placing a hand on her shoulder as support. “It could strike at any time”"));



        return lstBooks;
    }

}
