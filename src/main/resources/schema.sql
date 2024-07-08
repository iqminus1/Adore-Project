create unique index if not exists user_email_ui on users(email) where deleted = false;
create unique index if not exists user_username_ui on users(username) where deleted = false;
create unique index if not exists region_name_ui on region(name) where deleted = false;
create unique index if not exists neighbourhood_ui on neighbourhood(district_id,street,house_number, name) where deleted= false;
create unique index if not exists restaurant_ui on restaurant(neighbourhood_id) where deleted= false;
create unique index if not exists orders_ui on orders(date,status_enum,work_time) where deleted= false and status_enum in ('PENDING','APPROVED');
