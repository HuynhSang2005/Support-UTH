import { useState } from 'react';
import { Ticket, TicketStatus, TicketCategory } from '@/types/ticket';
import { TicketStatusBadge } from '@/components/tickets/TicketStatusBadge';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table';
import { Input } from '@/components/ui/input';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';

interface TicketDataGridProps {
  tickets: Ticket[];
  onTicketSelect: (ticket: Ticket) => void;
}

export function TicketDataGrid({ tickets, onTicketSelect }: TicketDataGridProps) {
  const [searchQuery, setSearchQuery] = useState('');
  const [statusFilter, setStatusFilter] = useState<TicketStatus | 'all'>('all');
  const [categoryFilter, setCategoryFilter] = useState<TicketCategory | 'all'>('all');

  const filteredTickets = tickets.filter(ticket => {
    const matchesSearch = ticket.title.toLowerCase().includes(searchQuery.toLowerCase());
    const matchesStatus = statusFilter === 'all' || ticket.status === statusFilter;
    const matchesCategory = categoryFilter === 'all' || ticket.category === categoryFilter;
    return matchesSearch && matchesStatus && matchesCategory;
  });

  return (
    <div className="space-y-4">
      <div className="flex gap-4">
        <Input
          placeholder="Tìm kiếm yêu cầu..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          className="max-w-sm"
        />
        <Select
          value={statusFilter}
          onValueChange={(value) => setStatusFilter(value as TicketStatus | 'all')}
        >
          <SelectTrigger className="w-[180px]">
            <SelectValue placeholder="Trạng thái" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">Tất cả</SelectItem>
            {Object.values(TicketStatus).map((status) => (
              <SelectItem key={status} value={status}>
                {status}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
        <Select
          value={categoryFilter}
          onValueChange={(value) => setCategoryFilter(value as TicketCategory | 'all')}
        >
          <SelectTrigger className="w-[180px]">
            <SelectValue placeholder="Danh mục" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">Tất cả</SelectItem>
            {Object.values(TicketCategory).map((category) => (
              <SelectItem key={category} value={category}>
                {category}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>

      <div className="rounded-md border">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>Tiêu đề</TableHead>
              <TableHead>Danh mục</TableHead>
              <TableHead>Trạng thái</TableHead>
              <TableHead>Ngày tạo</TableHead>
              <TableHead>Cập nhật</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {filteredTickets.map((ticket) => (
              <TableRow
                key={ticket.id}
                className="cursor-pointer hover:bg-muted/50"
                onClick={() => onTicketSelect(ticket)}
              >
                <TableCell className="font-medium">{ticket.id}</TableCell>
                <TableCell>{ticket.title}</TableCell>
                <TableCell>{ticket.category}</TableCell>
                <TableCell>
                  <TicketStatusBadge status={ticket.status} />
                </TableCell>
                <TableCell>
                  {new Date(ticket.createdAt).toLocaleDateString('vi-VN')}
                </TableCell>
                <TableCell>
                  {new Date(ticket.updatedAt).toLocaleDateString('vi-VN')}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}