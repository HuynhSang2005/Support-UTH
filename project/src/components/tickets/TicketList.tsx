import { Ticket } from '@/types/ticket';
import { TicketStatusBadge } from './TicketStatusBadge';

interface TicketListProps {
  tickets: Ticket[];
  onTicketClick?: (ticket: Ticket) => void;
}

export function TicketList({ tickets, onTicketClick }: TicketListProps) {
  return (
    <div className="space-y-4">
      {tickets.map((ticket) => (
        <div
          key={ticket.id}
          className="p-4 rounded-lg border bg-card hover:border-primary/50 transition-colors cursor-pointer"
          onClick={() => onTicketClick?.(ticket)}
        >
          <div className="flex items-center justify-between mb-2">
            <h3 className="font-medium">{ticket.title}</h3>
            <TicketStatusBadge status={ticket.status} />
          </div>
          <p className="text-sm text-muted-foreground mb-2 line-clamp-2">
            {ticket.description}
          </p>
          <div className="flex items-center justify-between text-xs text-muted-foreground">
            <span>Danh mục: {ticket.category}</span>
            <span>
              {new Date(ticket.createdAt).toLocaleDateString('vi-VN')}
            </span>
          </div>
        </div>
      ))}
    </div>
  );
}