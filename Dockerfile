# Base Java image
FROM openjdk:8-jdk-slim

# Set working directory
WORKDIR /app

# Copy the entire project into the container (including source code and build files)
COPY . .

# Ensure the scripts to compile and run the project are executable
RUN chmod +x /app/*.sh

# Run the compile script to generate the JAR file
RUN /app/compile.sh

# Specify the default command to start a shell
CMD ["bash"]
