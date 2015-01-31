class AddUserBirthday < ActiveRecord::Migration
  def up
      execute "ALTER TABLE user ADD COLUMN birthday VARCHAR(255) NOT NULL;"
  end

  def down
      execute "ALTER TABLE user DROP birthday;"
  end
end
