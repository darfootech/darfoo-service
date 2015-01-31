class CreateCrashlogMigration < ActiveRecord::Migration
  def up
      create_table :crash_log do |t|
          t.column :log, :text
          t.column :timestamp, :bigint
          t.column :due_date, :datetime
      end
  end

  def down
      drop_table :crash_log
  end
end
