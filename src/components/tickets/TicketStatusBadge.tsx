import { TicketStatus, ticketStatusLabels } from '../../types/tickets/ticket';
import { cn } from '../../lib/utils';

interface TicketStatusBadgeProps {
  status: TicketStatus;
}

const statusConfig = {
  [TicketStatus.PENDING]: {
    label: ticketStatusLabels[TicketStatus.PENDING],
    className: 'bg-yellow-100 text-yellow-800'
  },
  [TicketStatus.IN_PROGRESS]: {
    label: ticketStatusLabels[TicketStatus.IN_PROGRESS],
    className: 'bg-blue-100 text-blue-800'
  },
  [TicketStatus.RESOLVED]: {
    label: ticketStatusLabels[TicketStatus.RESOLVED],
    className: 'bg-green-100 text-green-800'
  },
  [TicketStatus.CLOSED]: {
    label: ticketStatusLabels[TicketStatus.CLOSED],
    className: 'bg-gray-100 text-gray-800'
  }
};

export function TicketStatusBadge({ status }: TicketStatusBadgeProps) {
  const config = statusConfig[status];
  
  return (
    <span className={cn(
      'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium',
      config.className
    )}>
      {config.label}
    </span>
  );
}