import { DashboardCard } from '@/components/dashboard/DashboardCard';
import { Ticket, TicketStatus } from '@/types/ticket';
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
} from 'recharts';
import { Card } from '@/components/ui/card';
import { ClipboardList, Clock, CheckCircle, BarChart2 } from 'lucide-react';

interface StaffMetricsProps {
  tickets: Ticket[];
}

export function StaffMetrics({ tickets }: StaffMetricsProps) {
  const activeTickets = tickets.filter(
    (t) => t.status !== TicketStatus.CLOSED && t.status !== TicketStatus.RESOLVED
  ).length;

  const resolvedToday = tickets.filter((t) => {
    const today = new Date();
    const ticketDate = new Date(t.updatedAt);
    return (
      t.status === TicketStatus.RESOLVED &&
      ticketDate.toDateString() === today.toDateString()
    );
  }).length;

  // Tính thời gian phản hồi trung bình (giả định)
  const avgResponseTime = '2.5 giờ';

  // Dữ liệu cho biểu đồ
  const categoryData = Object.entries(
    tickets.reduce((acc, ticket) => {
      acc[ticket.category] = (acc[ticket.category] || 0) + 1;
      return acc;
    }, {} as Record<string, number>)
  ).map(([name, value]) => ({ name, value }));

  return (
    <div className="space-y-6">
      <div className="grid gap-4 md:grid-cols-4">
        <DashboardCard
          title="Yêu cầu đang xử lý"
          value={activeTickets}
          icon={<ClipboardList className="h-4 w-4 text-muted-foreground" />}
        />
        <DashboardCard
          title="Đã giải quyết hôm nay"
          value={resolvedToday}
          icon={<CheckCircle className="h-4 w-4 text-muted-foreground" />}
        />
        <DashboardCard
          title="Thời gian phản hồi TB"
          value={avgResponseTime}
          icon={<Clock className="h-4 w-4 text-muted-foreground" />}
        />
        <DashboardCard
          title="Tổng số yêu cầu"
          value={tickets.length}
          icon={<BarChart2 className="h-4 w-4 text-muted-foreground" />}
        />
      </div>

      <Card className="p-4">
        <h3 className="font-semibold mb-4">Phân bố yêu cầu theo danh mục</h3>
        <div className="h-[300px]">
          <ResponsiveContainer width="100%" height="100%">
            <BarChart data={categoryData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="name" />
              <YAxis />
              <Tooltip />
              <Bar dataKey="value" fill="#4f46e5" />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </Card>
    </div>
  );
}