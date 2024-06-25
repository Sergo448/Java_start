class Books {
    String title;
    String author;
}

class BooksTestDrive {
    public static void main(String[] args) {
        Books [] myBooks = new Books[3];
        int x = 0;

        myBooks[0] = new Books();
        myBooks[1] = new Books();
        myBooks[2] = new Books();

        myBooks[0].title = "title_0";
        myBooks[1].title = "title_1";
        myBooks[2].title = "title_2";

        myBooks[0].author = "author_0";
        myBooks[1].author = "author_1";
        myBooks[2].author = "author_2";

        while (x < 3) {
            System.out.println(myBooks[x].title + " by " + myBooks[x].author);
            x += 1;
        }
    }
}