import { useForm, Controller } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import * as z from 'zod';
import { Button } from '../../../../components/ui/button';
import { Input } from '../../../../components/ui/input';
import { Label } from '../../../../components/ui/label';
import { Textarea } from '../../../../components/ui/textarea';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '../../../../components/ui/select';
import { useToast } from '../../../../components/ui/use-toast';
import { TicketCategory, categoryLabels } from '../../../../types/tickets/categories';
import { ticketSchema } from '../../../../types/tickets/ticket';

type TicketFormData = z.infer<typeof ticketSchema>;

interface TicketFormProps {
  onSubmit: (data: TicketFormData) => void;
  onCancel: () => void;
}

export function TicketForm({ onSubmit, onCancel }: TicketFormProps) {
  const { toast } = useToast();
  const {
    register,
    handleSubmit,
    control,
    watch,
    reset,
    formState: { errors },
  } = useForm<TicketFormData>({
    resolver: zodResolver(ticketSchema),
  });

  const selectedCategory = watch('category');

  const handleFormSubmit = (data: TicketFormData) => {
    try {
      onSubmit(data);
      reset();
      toast({
        title: 'Gửi yêu cầu thành công',
        description: 'Yêu cầu của bạn đã được ghi nhận',
      });
    } catch (error) {
      toast({
        variant: 'destructive',
        title: 'Lỗi',
        description: 'Có lỗi xảy ra khi gửi yêu cầu',
      });
    }
  };

  return (
    <form onSubmit={handleSubmit(handleFormSubmit)} className="space-y-6">
      <div className="space-y-2">
        <Label htmlFor="title">Tiêu đề</Label>
        <Input
          id="title"
          {...register('title')}
          placeholder="Nhập tiêu đề yêu cầu"
        />
        {errors.title && (
          <p className="text-sm text-red-500">{errors.title.message}</p>
        )}
      </div>

      <div className="space-y-2">
        <Label htmlFor="category">Danh mục</Label>
        <Controller
          name="category"
          control={control}
          render={({ field }) => (
            <Select
              onValueChange={field.onChange}
              value={field.value}
              defaultValue={field.value}
            >
              <SelectTrigger>
                <SelectValue placeholder="Chọn danh mục" />
              </SelectTrigger>
              <SelectContent>
                {Object.entries(categoryLabels).map(([key, label]) => (
                  <SelectItem key={key} value={key}>
                    {label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          )}
        />
        {errors.category && (
          <p className="text-sm text-red-500">{errors.category.message}</p>
        )}
      </div>
      
      <div className="space-y-2">
        <Label htmlFor="description">Mô tả chi tiết</Label>
        <Textarea
          id="description"
          {...register('description')}
          placeholder="Mô tả chi tiết vấn đề của bạn"
          rows={5}
        />
        {errors.description && (
          <p className="text-sm text-red-500">{errors.description.message}</p>
        )}
      </div>

      <div className="flex justify-end gap-4">
        <Button type="button" variant="outline" onClick={onCancel}>
          Hủy
        </Button>
        <Button type="button" variant="outline" onClick={() => reset()}>
          Đặt lại
        </Button>
        <Button type="submit">Gửi yêu cầu</Button>
      </div>
    </form>
  );
}