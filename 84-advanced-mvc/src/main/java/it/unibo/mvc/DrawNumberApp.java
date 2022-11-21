package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *            the views to attach
     */
    public DrawNumberApp(final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        final InputStream input = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("config.yml"));
        final var cBuilder = new Configuration.Builder();
        try(var buffReader = new BufferedReader(new InputStreamReader(input))){
            String line;
            while ((line = buffReader.readLine()) != null){
                final StringTokenizer st = new StringTokenizer(line);
                final String next = st.nextToken();
                if("minimum:".equals(next)){
                    cBuilder.setMin(Integer.parseInt(st.nextToken()));
                }
                if("maximum:".equals(next)){
                    cBuilder.setMax(Integer.parseInt(st.nextToken()));
                }
                if("attempts:".equals(next)){
                    cBuilder.setAttempts(Integer.parseInt(st.nextToken()));
                }
            }
        } catch (final IOException e){
            System.out.println(e.getMessage());
        }
        final Configuration configuration = cBuilder.build();
        if(configuration.isConsistent()){
            this.model = new DrawNumberImpl(configuration);
        } 
        else{
            this.model = new DrawNumberImpl(new Configuration.Builder().build());
        }
    }


    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl());
        new PrintStreamView (System.out);
        new PrintStreamView("output.log");
    }

}
