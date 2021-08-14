package example;

public interface ExchangeNames {

    interface Topic {
        String Topic1 = "T_topic1";
        String Topic2 = "T_topic2";

        interface RoutingKey {
            String T1 = "T1";
            String T2 = "T2";
        }
    }

    interface Fanout {
        String Fanout1 = "F_fanout1";
        String Fanout2 = "F_fanout2";

        interface RoutingKey {
            String F1 = "F1";
            String F2 = "F2";
        }
    }

    interface Headers {
        String Header1 = "H_header1";
        String Header2 = "H_header2";
    }
}
