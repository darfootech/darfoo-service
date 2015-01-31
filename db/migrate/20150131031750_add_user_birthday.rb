class AddUserBirthday < ActiveRecord::Migration
  def up
      execute "ALTER TABLE user ADD COLUMN birthday VARCHAR(255)"
  end

  def down
      execute "ALTER TABLE user DROP birthday"
  end
end
