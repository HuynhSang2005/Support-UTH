import { Ticket } from '../../../../types/tickets/ticket';
import { TicketStatusBadge } from './../../../../components/tickets/TicketStatusBadge';
import { Card, CardHeader, CardContent } from '../../../../components/ui/card';
import { categoryLabels } from '../../../../types/tickets/categories';

interface TicketCardProps {
  ticket: Ticket;
  onClick: () => void;
}

export function TicketCard({ ticket, onClick }: TicketCardProps) {
  return (
    <Card
      className="cursor-pointer hover:border-primary/50 transition-colors"
      onClick={onClick}
    >
      <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
        <h3 className="font-medium">{ticket.title}</h3>
        <TicketStatusBadge status={ticket.status} />
      </CardHeader>
      <CardContent>
        <p className="text-sm text-muted-foreground line-clamp-2">
          {ticket.description}
        </p>
        <div className="mt-2 flex items-center justify-between text-xs text-muted-foreground">
          <span>Danh mục: {categoryLabels[ticket.category]}</span>
          <span>
            {new Date(ticket.createdAt).toLocaleDateString('vi-VN')}
          </span>
        </div>
      </CardContent>
    </Card>
  );
}