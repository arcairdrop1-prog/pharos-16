import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WhaleWatcherAgent {

    // Requirement 02: Configurable significant threshold in USD value
    private static final double SIGNIFICANT_THRESHOLD_USD = 50000.0;

    // Requirement 01: Tracks at least 5 configurable addresses
    private final List<String> watchedAddresses = new ArrayList<>();

    public static class WhaleMovement {
        LocalDateTime timestamp;
        String fromAddress;
        String toAddress;
        String token;
        double amount;
        double valueUSD;
        boolean isSignificant;

        public WhaleMovement(String from, String to, String token, double amount, double valueUSD) {
            this.timestamp = LocalDateTime.now();
            this.fromAddress = from;
            this.toAddress = to;
            this.token = token;
            this.amount = amount;
            this.valueUSD = valueUSD;
            this.isSignificant = valueUSD >= SIGNIFICANT_THRESHOLD_USD;
        }

        // Requirement 03: Explains what moved, amount, and labeled destination/source
        public String getExplanation() {
            return String.format("[%s] Transferred %.2f %s ($%.2f USD) from %s to %s -> %s",
                    timestamp, amount, token, valueUSD, fromAddress, toAddress,
                    isSignificant ? "🚨 SIGNIFICANT WHALE MOVEMENT" : "ℹ️ Normal Activity");
        }
    }

    private final List<WhaleMovement> history = new ArrayList<>();

    public WhaleWatcherAgent() {
        // Requirement 01: Initializing at least 5 watched whale addresses
        watchedAddresses.add("0xWhaleWallet1_BinanceHot");
        watchedAddresses.add("0xWhaleWallet2_WhaleFund");
        watchedAddresses.add("0xWhaleWallet3_DeFiVault");
        watchedAddresses.add("0xWhaleWallet4_GenesisHolder");
        watchedAddresses.add("0xWhaleWallet5_ProtocolTreasury");
    }

    public void trackMovement(String from, String to, String token, double amount, double valueUSD) {
        WhaleMovement movement = new WhaleMovement(from, to, token, amount, valueUSD);
        history.add(movement);
        System.out.println(movement.getExplanation());
    }

    // Requirement 04: Produces a summarized view of recent activity across all watched addresses
    public void generateSummaryReport() {
        System.out.println("\n============================================================");
        System.out.println("📊 WHALE ACTIVITY SUMMARY REPORT");
        System.out.println("============================================================");
        System.out.println("Total Monitored Addresses: " + watchedAddresses.size());
        System.out.println("Total Tracked Movements: " + history.size());
        
        long significantCount = history.stream().filter(h -> h.isSignificant).count();
        System.out.println("Significant Whale Movements Detected: " + significantCount);
        
        System.out.println("\nDetailed Recent Activity Log:");
        for (WhaleMovement m : history) {
            System.out.println(" - " + m.getExplanation());
        }
        System.out.println("============================================================");
    }

    public static void main(String[] args) {
        WhaleWatcherAgent agent = new WhaleWatcherAgent();

        System.out.println("=== STARTING WHALE WATCHER AGENT (TESTNET SIMULATION) ===");

        // Simulating transactions across tracked addresses
        agent.trackMovement("0xWhaleWallet1_BinanceHot", "0xWhaleWallet2_WhaleFund", "USDC", 75000.0, 75000.0);
        agent.trackMovement("0xWhaleWallet3_DeFiVault", "0xExternalExchange", "ETH", 10.0, 32000.0);
        agent.trackMovement("0xWhaleWallet4_GenesisHolder", "0xWhaleWallet5_ProtocolTreasury", "PHAROS", 250000.0, 120000.0);

        // Generate summarized view across all watched addresses (Requirement 04)
        agent.generateSummaryReport();
    }
}
