resource "aws_sqs_queue" "data-process-request-streaming-queue" {
  name                      = "data-process-request-streaming-queue"
  delay_seconds             = 0
  max_message_size          = 262144
  message_retention_seconds = 345600
  receive_wait_time_seconds = 0
  redrive_policy = jsonencode({
    deadLetterTargetArn = aws_sqs_queue.terraform_queue_deadletter.arn
    maxReceiveCount     = 4
  })

    tags = {
    app = "data-process-service",
    env = "development"
  }
}