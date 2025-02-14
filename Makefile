# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: jm <jm@student.42lyon.fr>                  +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2024/04/11 20:26:23 by TheTerror         #+#    #+#              #
#    Updated: 2025/02/13 19:56:58 by jm               ###   ########lyon.fr    #
#                                                                              #
# **************************************************************************** #

# Color codes for stylish output
RESET   = \033[0m
BOLD    = \033[1m
RED     = \033[31m
GREEN   = \033[32m
YELLOW  = \033[33m
BLUE    = \033[34m
CYAN    = \033[36m
WHITE   = \033[97m


NAME			= avaj-launcher
JC				= javac
JRUN			= java
JFLAGS			= 

BIN_DIR			= .bin

MAIN_PACKAGE	= avajlauncher.main
MAIN_CLASS		= $(MAIN_PACKAGE).Main

SRC				= $(shell find * -name "*.java")
CLASS			= $(addprefix $(BIN_DIR)/, $(SRC:.java=.class))

RM				= rm -rf

# Rule to compile .java files to .class files
$(BIN_DIR)/%.class : %.java
	@echo "$(CYAN)Compiling $< to $@$(RESET)"
	@$(JC) $(JFLAGS) $< -d $(BIN_DIR)

### Targets ###
all : $(NAME)

$(NAME) : $(CLASS)
	@echo "$(GREEN)Running the program $(MAIN_CLASS)$(RESET)"
	@$(JRUN) -cp $(BIN_DIR) $(MAIN_CLASS)

$(BIN_DIR):
	@mkdir -p $(BIN_DIR)


clean:
	@echo "$(RED)Cleaning up class files...$(RESET)"
	$(RM) $(BIN_DIR)

fclean: clean
	@echo "$(RED)Fully cleaned.$(RESET)"

re: log_re fclean all

log_re:
	@echo "$(BLUE)Rebuilding everything...$(RESET)"

.PHONY: all clean fclean re log_re
