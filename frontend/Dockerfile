# Build stage
FROM node:slim AS build
WORKDIR /app

# Copy package files first for better caching
COPY package*.json ./

# Install dependencies and Angular CLI globally
RUN npm install
RUN npm install -g @angular/cli@latest

# Copy the rest of the application
COPY . .

# Build the application specifying the project explicitly
RUN ng build --configuration=production --project=frontend

# Run stage
FROM nginx:1.27.4-alpine-slim
COPY --from=build /app/dist/frontend /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]