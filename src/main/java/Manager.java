import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class Manager implements LifecycleManager {
    @Override
    public void start() {
        String _consumerKey = System.getenv().get("TWITTER_CONSUMER_KEY");
        String _consumerSecret = System.getenv().get("TWITTER_CONSUMER_SECRET");
        String _accessToken = System.getenv().get("TWITTER_ACCESS_TOKEN");
        String _accessTokenSecret = System.getenv().get("TWITTER_ACCESS_TOKEN_SECRET");
        TwitterStream ts = getTwitterStreamInstance(_consumerKey,_consumerSecret, _accessToken, _accessTokenSecret);
        ts.addListener(listener);
    }

    @Override
    public void stop() {

    }

    private static TwitterStream getTwitterStreamInstance(String _consumerKey, String _consumerSecret, String _accessToken, String _accessTokenSecret) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(_consumerKey)
                .setOAuthConsumerSecret(_consumerSecret)
                .setOAuthAccessToken(_accessToken)
                .setOAuthAccessTokenSecret(_accessTokenSecret);
        TwitterStreamFactory tf = new TwitterStreamFactory(configurationBuilder.build());
        return tf.getInstance();
    }

    private StatusListener listener = new StatusListener() {
        @Override
        public void onStatus(Status status) {
            Tweet tweet = new Tweet();
            tweet.setUsername(status.getUser().getName());
            tweet.setText(status.getText());
            tweet.setDate(status.getCreatedAt());
        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

        }

        @Override
        public void onTrackLimitationNotice(int i) {

        }

        @Override
        public void onScrubGeo(long l, long l1) {

        }

        @Override
        public void onStallWarning(StallWarning stallWarning) {

        }

        @Override
        public void onException(Exception e) {
            e.printStackTrace();
        }
    };
}

