import { z } from 'zod';
import { categoryFields, TicketCategory } from './categories';

export const createCategoryValidationSchema = (category: TicketCategory) => {
  const fields = categoryFields[category];
  const schemaFields: Record<string, z.ZodType<any>> = {};

  fields.forEach(field => {
    if (field.required) {
      schemaFields[field.name] = z.string().min(1, `${field.label} là bắt buộc`);
    } else {
      schemaFields[field.name] = z.string().optional();
    }
  });

  return z.object(schemaFields);
};

export const validateCategoryData = (
  category: TicketCategory,
  data: Record<string, string>
) => {
  const schema = createCategoryValidationSchema(category);
  return schema.safeParse(data);
};