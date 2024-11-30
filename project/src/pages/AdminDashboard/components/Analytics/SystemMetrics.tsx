import { DashboardCard } from '@/components/dashboard/DashboardCard';
import { Card } from '@/components/ui/card';
import {
  Users,
  Activity,
  Clock,
  BarChart2,
  CheckCircle,
} from 'lucide-react';
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
} from 'recharts';

const mockData = [
  { name: 'T2', value: 24 },
  { name: 'T3', value: 35 },
  { name: 'T4', value: 30 },
  { name: 'T5', value: 45 },
  { name: 'T6', value: 38 },
  { name: 'T7', value: 25 },
  { name: 'CN', value: 32 },
];

export function SystemMetrics() {
  return (
    <div className="space-y-6">
      <div className="grid gap-4 md:grid-cols-5">
        <DashboardCard
          title="Tổng người dùng"
          value="1,234"
          icon={<Users className="h-4 w-4 text-muted-foreground" />}
        />
        <DashboardCard
          title="Đang hoạt động"
          value="45"
          icon={<Activity className="h-4 w-4 text-muted-foreground" />}
        />
        <DashboardCard
          title="Tỷ lệ giải quyết"
          value="92%"
          icon={<CheckCircle className="h-4 w-4 text-muted-foreground" />}
        />
        <DashboardCard
          title="Thời gian phản hồi"
          value="2.5h"
          icon={<Clock className="h-4 w-4 text-muted-foreground" />}
        />
        <DashboardCard
          title="Yêu cầu mới"
          value="28"
          icon={<BarChart2 className="h-4 w-4 text-muted-foreground" />}
        />
      </div>

      <Card className="p-4">
        <h3 className="font-semibold mb-4">Xu hướng yêu cầu hỗ trợ</h3>
        <div className="h-[300px]">
          <ResponsiveContainer width="100%" height="100%">
            <LineChart data={mockData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="name" />
              <YAxis />
              <Tooltip />
              <Line
                type="monotone"
                dataKey="value"
                stroke="#4f46e5"
                strokeWidth={2}
              />
            </LineChart>
          </ResponsiveContainer>
        </div>
      </Card>
    </div>
  );
}