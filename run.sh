#!/bin/bash
# Utility script for managing containers

set -euo pipefail

# Docker Compose files
dc_infra=docker-compose.yml
dc_app=docker-compose-app.yml

# Build the API with Maven
build_api() {
    echo "[INFO] Building API with Maven..."
    (cd bookmarker-api && ./mvnw clean package -DskipTests)
    echo "[INFO] API build complete."
}

# Start only infrastructure (DB, cache, etc.)
start_infra() {
    echo "[INFO] Starting infrastructure containers..."
    docker compose -f ${dc_infra} up -d
    echo "[INFO] Infrastructure started. Showing logs..."
    docker compose -f ${dc_infra} logs -f
}

# Stop only infrastructure
stop_infra() {
    echo "[INFO] Stopping infrastructure containers..."
    docker compose -f ${dc_infra} down
    echo "[INFO] Infrastructure stopped and removed."
}

# Start API + Infra
start() {
    echo "[INFO] Starting API and Infrastructure..."
    build_api
    docker compose -f ${dc_infra} -f ${dc_app} up --build -d
    echo "[INFO] API + Infra started. Showing logs..."
    docker compose -f ${dc_infra} -f ${dc_app} logs -f
}

# Stop API + Infra
stop() {
    echo "[INFO] Stopping API and Infrastructure..."
    docker compose -f ${dc_infra} -f ${dc_app} down
    echo "[INFO] API + Infra stopped and removed."
}

# Restart everything
restart() {
    echo "[INFO] Restarting everything..."
    stop
    sleep 2
    start
}

# Help menu
help() {
    echo "Usage: ./run.sh [command]"
    echo "Commands:"
    echo "  start_infra   - Start only infra (DB/cache)"
    echo "  stop_infra    - Stop only infra"
    echo "  start         - Start API + infra"
    echo "  stop          - Stop API + infra"
    echo "  restart       - Restart everything"
    echo "  build_api     - Build API only"
    echo "  help          - Show this message"
}

# Run chosen command
if declare -f "$1" >/dev/null 2>&1; then
    "$@"
else
    echo "[ERROR] Unknown command: $*"
    help
    exit 1
fi
