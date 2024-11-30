import { useState } from 'react';
import { Ticket } from '@/types/ticket';
import { TicketDataGrid } from './components/TicketDataGrid';
import { TicketDetail } from './components/TicketDetail';
import { StaffMetrics } from './components/StaffMetrics';

export default function StaffDashboard() {
  const [tickets] = useState<Ticket[]>([]); // Will be replaced with actual data
  const [selectedTicket, setSelectedTicket] = useState<Ticket | null>(null);

  return (
    <div className="space-y-6">
      <h1 className="text-3xl font-bold">Quản lý yêu cầu hỗ trợ</h1>
      
      <StaffMetrics tickets={tickets} />

      <div className="grid gap-6 lg:grid-cols-2">
        <div className="space-y-4">
          <h2 className="text-xl font-semibold">Danh sách yêu cầu</h2>
          <TicketDataGrid
            tickets={tickets}
            onTicketSelect={setSelectedTicket}
          />
        </div>

        <div className="space-y-4">
          <h2 className="text-xl font-semibold">Chi tiết yêu cầu</h2>
          {selectedTicket ? (
            <TicketDetail
              ticket={selectedTicket}
              onStatusChange={(status) => {
                console.log('Status changed:', status);
              }}
              onCommentSubmit={(comment) => {
                console.log('New comment:', comment);
              }}
            />
          ) : (
            <div className="rounded-lg border p-8 text-center text-muted-foreground">
              Chọn một yêu cầu để xem chi tiết
            </div>
          )}
        </div>
      </div>
    </div>
  );
}