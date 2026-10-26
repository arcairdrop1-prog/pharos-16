# Pharos Whale Watcher Agent

An intelligent monitoring agent developed in **Java** for the Pharos Agent Arena designed to track large wallet addresses and report significant market movements.

## 📋 Requirements Met

1. **Multi-Address Tracking (Req. 01):** Tracks a configurable list of at least 5 whale addresses.
2. **Configurable Significance Threshold (Req. 02):** Evaluates transactions against a configurable USD or token value threshold.
3. **Detailed Movement Explanation (Req. 03):** Explains every movement detailing what token/amount was transferred, origin, and labeled destination.
4. **Summarized Activity View (Req. 04):** Produces a comprehensive summary report of recent activity across all monitored addresses.

## 🚀 How to Run

1. Certifique-se de ter o **Java JDK** instalado.
2. Compile o ficheiro:
   ```bash
   javac WhaleWatcherAgent.java
