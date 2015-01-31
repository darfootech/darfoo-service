# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 0) do

  create_table "author", :force => true do |t|
    t.string  "DESCRIPTION",                             :null => false
    t.string  "NAME",                                    :null => false
    t.integer "IMAGE_ID"
    t.integer "HOTTEST",     :limit => 8, :default => 0
  end

  add_index "author", ["IMAGE_ID"], :name => "FK_oawshgflct2lkk4aj6cx7truw"

  create_table "bind", :force => true do |t|
    t.integer  "user_id",   :limit => 8
    t.string   "mac",                    :null => false
    t.integer  "timestamp", :limit => 8
    t.datetime "due_date"
  end

  add_index "bind", ["mac"], :name => "uq_bind_mac", :unique => true

  create_table "dancegroup", :force => true do |t|
    t.string  "DESCRIPTION",                   :null => false
    t.string  "NAME",                          :null => false
    t.integer "UPDATE_TIMESTAMP", :limit => 8, :null => false
    t.integer "IMAGE_ID"
  end

  add_index "dancegroup", ["IMAGE_ID"], :name => "FK_opb0b654ku6rhgrm028bv86ht"
  add_index "dancegroup", ["NAME"], :name => "UK_ktwgfl0yy41oau2cnqgdan9st", :unique => true

  create_table "dancegroupimage", :force => true do |t|
    t.string "IMAGE_KEY", :null => false
  end

  add_index "dancegroupimage", ["IMAGE_KEY"], :name => "UK_n4ri2p3qjxros5dd3qd00ofgs", :unique => true

  create_table "education", :force => true do |t|
    t.string  "TITLE",                                        :null => false
    t.integer "UPDATE_TIMESTAMP", :limit => 8,                :null => false
    t.string  "VIDEO_KEY",                                    :null => false
    t.integer "AUTHOR_ID"
    t.integer "IMAGE_ID"
    t.integer "MUSIC_ID"
    t.integer "HOTTEST",          :limit => 8, :default => 0
  end

  add_index "education", ["AUTHOR_ID"], :name => "FK_3h09bgoo42fmqdwv3qcbfyowx"
  add_index "education", ["IMAGE_ID"], :name => "FK_sulggpjwxhfc3crcw9kb8nd0q"
  add_index "education", ["MUSIC_ID"], :name => "FK_49vibr012ja4lnyyyc4ieglw7"
  add_index "education", ["VIDEO_KEY"], :name => "UK_k5yi8l1xjutsbx1n9eb6b7fts", :unique => true

  create_table "education_category", :id => false, :force => true do |t|
    t.integer "category_id", :null => false
    t.integer "video_id",    :null => false
  end

  add_index "education_category", ["category_id"], :name => "FK_mke6t2olugmm8mwrn5ep706ur"
  add_index "education_category", ["video_id"], :name => "FK_sa78me9tvbu943uc59dfgew7a"

  create_table "educationcategory", :force => true do |t|
    t.string "DESCRIPTION", :null => false
    t.string "TITLE",       :null => false
  end

  add_index "educationcategory", ["TITLE"], :name => "UK_lyop6lv5y9pqlfxh58efqpsa0", :unique => true

  create_table "feed_back", :force => true do |t|
    t.integer  "user_id",   :limit => 8
    t.string   "username"
    t.string   "feedback"
    t.integer  "timestamp", :limit => 8
    t.datetime "due_date"
  end

  create_table "image", :force => true do |t|
    t.string "IMAGE_KEY", :null => false
  end

  add_index "image", ["IMAGE_KEY"], :name => "UK_btc8b2kp40nkikcgs27phuhvu", :unique => true

  create_table "menu", :force => true do |t|
    t.string  "mac"
    t.string  "hostip"
    t.string  "uuid"
    t.integer "menuid"
    t.integer "clickcount", :limit => 8
  end

  create_table "menu_time", :force => true do |t|
    t.string   "mac"
    t.string   "hostip"
    t.string   "uuid"
    t.integer  "menuid"
    t.integer  "timestamp", :limit => 8
    t.datetime "due_date"
  end

  create_table "music", :force => true do |t|
    t.string  "MUSIC_KEY",                                    :null => false
    t.string  "TITLE",                                        :null => false
    t.integer "UPDATE_TIMESTAMP", :limit => 8,                :null => false
    t.integer "AUTHOR_ID"
    t.integer "IMAGE_ID"
    t.string  "AUTHOR_NAME",                                  :null => false
    t.integer "HOTTEST",          :limit => 8, :default => 0
  end

  add_index "music", ["AUTHOR_ID"], :name => "FK_cf2agfg6jawkf17jx3dd5vogl"
  add_index "music", ["IMAGE_ID"], :name => "FK_1n0xyfadw1t7csqer5y3wi0a4"

  create_table "music_category", :id => false, :force => true do |t|
    t.integer "category_id", :null => false
    t.integer "music_id",    :null => false
  end

  add_index "music_category", ["category_id"], :name => "FK_oavu6676xn8hfomrmmlo9qdni"
  add_index "music_category", ["music_id"], :name => "FK_leia7b41xtkbc1hrxm9jdxdbn"

  create_table "musiccategory", :force => true do |t|
    t.string "DESCRIPTION", :null => false
    t.string "Title",       :null => false
  end

  add_index "musiccategory", ["Title"], :name => "UK_gfd7ay99petp52qo60abbh79e", :unique => true

  create_table "play_evolutions", :force => true do |t|
    t.string    "hash",          :null => false
    t.timestamp "applied_at",    :null => false
    t.text      "apply_script"
    t.text      "revert_script"
    t.string    "state"
    t.text      "last_problem"
  end

  create_table "resource", :force => true do |t|
    t.string  "mac"
    t.string  "hostip"
    t.string  "uuid"
    t.string  "type"
    t.integer "resourceid"
    t.integer "clickcount", :limit => 8
  end

  create_table "resource_time", :force => true do |t|
    t.string   "mac"
    t.string   "hostip"
    t.string   "uuid"
    t.string   "type"
    t.integer  "resourceid"
    t.integer  "timestamp",  :limit => 8
    t.datetime "due_date"
  end

  create_table "search", :force => true do |t|
    t.string   "searchcontent"
    t.string   "searchtype"
    t.integer  "timestamp",     :limit => 8
    t.datetime "due_date"
  end

  create_table "tab", :force => true do |t|
    t.string  "mac"
    t.string  "hostip"
    t.string  "uuid"
    t.integer "tabid"
    t.integer "clickcount", :limit => 8
  end

  create_table "tab_time", :force => true do |t|
    t.string   "mac"
    t.string   "hostip"
    t.string   "uuid"
    t.integer  "tabid"
    t.integer  "timestamp", :limit => 8
    t.datetime "due_date"
  end

  create_table "uploadnoauthvideo", :force => true do |t|
    t.string  "MAC_ADDR",                   :null => false
    t.string  "VIDEO_KEY",                  :null => false
    t.integer "VIDEO_ID",    :default => 0, :null => false
    t.string  "VIDEO_TITLE",                :null => false
    t.string  "IMAGE_KEY",                  :null => false
    t.string  "VIDEO_TYPE",                 :null => false
  end

  add_index "uploadnoauthvideo", ["VIDEO_KEY"], :name => "UK_bp9gxn29c5mjyjm7qxxo9p56y", :unique => true

  create_table "uploadvideo", :force => true do |t|
    t.integer "USER_ID",   :default => 0, :null => false
    t.string  "VIDEO_KEY",                :null => false
    t.integer "VIDEO_ID",  :default => 0, :null => false
  end

  add_index "uploadvideo", ["VIDEO_KEY"], :name => "UK_s26agd4lrfo5qelkqqlyolmbi", :unique => true

  create_table "user", :force => true do |t|
    t.string   "username",               :null => false
    t.string   "password"
    t.integer  "timestamp", :limit => 8
    t.datetime "due_date"
  end

  add_index "user", ["username"], :name => "uq_user_username", :unique => true

  create_table "version", :force => true do |t|
    t.string "version", :null => false
  end

  create_table "video", :force => true do |t|
    t.string  "TITLE",                                        :null => false
    t.integer "UPDATE_TIMESTAMP", :limit => 8,                :null => false
    t.string  "VIDEO_KEY",                                    :null => false
    t.integer "AUTHOR_ID"
    t.integer "IMAGE_ID"
    t.integer "MUSIC_ID"
    t.integer "HOTTEST",          :limit => 8, :default => 0
  end

  add_index "video", ["AUTHOR_ID"], :name => "FK_g4njk6simvjou38x6hoqbjblo"
  add_index "video", ["IMAGE_ID"], :name => "FK_fr3ilu8k9b10a2b6pg3npgqoo"
  add_index "video", ["MUSIC_ID"], :name => "FK_5c6euhx7ex1dy5cql2wg3rs0r"
  add_index "video", ["VIDEO_KEY"], :name => "UK_ri41vxbkh8bit6dpbqa9quxep", :unique => true

  create_table "video_category", :id => false, :force => true do |t|
    t.integer "category_id", :null => false
    t.integer "video_id",    :null => false
  end

  add_index "video_category", ["category_id"], :name => "FK_32ph02gpmt2rhaapcoxatvs3s"
  add_index "video_category", ["video_id"], :name => "FK_so046v870uwktu6s53ukm8syi"

  create_table "videocategory", :force => true do |t|
    t.string "DESCRIPTION", :null => false
    t.string "TITLE",       :null => false
  end

  add_index "videocategory", ["TITLE"], :name => "UK_kw521qb5o9sdhg6q5jdgv8hql", :unique => true

end
