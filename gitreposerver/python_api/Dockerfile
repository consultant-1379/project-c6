FROM python:3.7-alpine
COPY . /app
WORKDIR /app
RUN apk add --no-cache gcc musl-dev linux-headers
RUN apk add && apk update && apk add git
RUN pip install -r requirements.txt
ENTRYPOINT ["python"]
CMD ["app.py"]