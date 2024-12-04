import { Ticket, TicketStatus } from '../../../types/tickets/ticket';
import { DashboardCard } from '../../../components/dashboard/DashboardCard';
import { TicketList } from '../components/Home/TicketList';
import { useTicketContext } from '../../../contexts/TicketContext';
import { TicketIcon, ClockIcon, CheckCircleIcon } from 'lucide-react';

export default function StudentHome() {
  const { tickets } = useTicketContext();

  const pendingTickets = tickets.filter(
    (ticket: Ticket) => ticket.status === TicketStatus.PENDING
  ).length;

  const resolvedTickets = tickets.filter(
    (ticket: Ticket) => ticket.status === TicketStatus.RESOLVED
  ).length;

  return (
    <div className="space-y-6">
      <h1 className="text-3xl font-bold">Trang chủ</h1>
      
      <div className="grid gap-4 md:grid-cols-3">
        <DashboardCard
          title="Tổng số yêu cầu"
          value={tickets.length}
          icon={<TicketIcon className="h-4 w-4 text-muted-foreground" />}
        />
        <DashboardCard
          title="Yêu cầu đang chờ"
          value={pendingTickets}
          icon={<ClockIcon className="h-4 w-4 text-muted-foreground" />}
        />
        <DashboardCard
          title="Yêu cầu đã giải quyết"
          value={resolvedTickets}
          icon={<CheckCircleIcon className="h-4 w-4 text-muted-foreground" />}
        />
      </div>

      <div className="space-y-4">
        <h2 className="text-xl font-semibold">Yêu cầu gần đây</h2>
        <TicketList
          tickets={tickets}
          onTicketClick={(ticket: Ticket) => {
            console.log('Clicked ticket:', ticket);
          }}
        />
      </div>
    </div>
  );
}