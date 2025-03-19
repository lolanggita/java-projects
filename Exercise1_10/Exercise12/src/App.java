package Exercise1_10.Exercise12.src;
public class App {
    public static void main(String[] args) throws Exception {
        Author anAuthor = new Author("Billy Jully Donh",  "", 'L');
        Author anAuthor2 = new Author("Kaenwe", "kaenwee@youknowiknow.co.id", 'P');

        anAuthor.setEmail("JohnDoeb@gamailin.com");
        System.out.println(anAuthor);
        System.out.println(anAuthor2);
    }
}
