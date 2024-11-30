import { useState } from 'react';
import { Ticket, TicketStatus, TicketComment } from '@/types/ticket';
import { Button } from '@/components/ui/button';
import { Textarea } from '@/components/ui/textarea';
import { TicketStatusBadge } from '@/components/tickets/TicketStatusBadge';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';

interface TicketDetailProps {
  ticket: Ticket;
  onStatusChange: (status: TicketStatus) => void;
  onCommentSubmit: (comment: string) => void;
}

export function TicketDetail({
  ticket,
  onStatusChange,
  onCommentSubmit,
}: TicketDetailProps) {
  const [comment, setComment] = useState('');

  const handleSubmit = () => {
    if (comment.trim()) {
      onCommentSubmit(comment);
      setComment('');
    }
  };

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h2 className="text-2xl font-bold">{ticket.title}</h2>
        <Select
          value={ticket.status}
          onValueChange={(value) => onStatusChange(value as TicketStatus)}
        >
          <SelectTrigger className="w-[180px]">
            <SelectValue>
              <TicketStatusBadge status={ticket.status} />
            </SelectValue>
          </SelectTrigger>
          <SelectContent>
            {Object.values(TicketStatus).map((status) => (
              <SelectItem key={status} value={status}>
                <TicketStatusBadge status={status} />
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>

      <div className="space-y-2">
        <h3 className="font-semibold">Mô tả</h3>
        <p className="text-muted-foreground">{ticket.description}</p>
      </div>

      <div className="space-y-4">
        <h3 className="font-semibold">Bình luận</h3>
        <div className="space-y-4">
          {ticket.comments.map((comment: TicketComment) => (
            <div key={comment.id} className="rounded-lg border p-4">
              <div className="flex items-center justify-between mb-2">
                <span className="font-medium">{comment.userId}</span>
                <span className="text-sm text-muted-foreground">
                  {new Date(comment.createdAt).toLocaleString('vi-VN')}
                </span>
              </div>
              <p>{comment.content}</p>
            </div>
          ))}
        </div>

        <div className="space-y-2">
          <Textarea
            placeholder="Nhập phản hồi của bạn..."
            value={comment}
            onChange={(e) => setComment(e.target.value)}
          />
          <Button onClick={handleSubmit}>Gửi phản hồi</Button>
        </div>
      </div>
    </div>
  );
}