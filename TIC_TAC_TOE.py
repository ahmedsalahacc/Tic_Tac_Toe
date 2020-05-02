#Board setting
from IPython.display import clear_output

def display_board(board):
    '''Takes the board and returns the template
    '''
    clear_output()
    print(f' {board[7]}    ||  {board[8]}    || {board[9]}   ')
    print('=======================')
    print(f' {board[4]}    ||  {board[5]}    || {board[6]}   ')
    print('=======================')
    print(f' {board[1]}    ||  {board[2]}    || {board[3]}   ')
    

#Take player input and assign their markers as (x or o)
def player_input():
    '''
    return player1_marker, player2_marker if was X or O
    '''
    marker=None
    while marker !='X' and marker !='O':
        marker=input('Player 1 Marker X or O: ').upper()
    if marker== 'X':
        player1_marker=marker
        player2_marker='O'
        return player1_marker, player2_marker
    else:
        player1_marker=marker
        player2_marker='X' 
        return player1_marker, player2_marker


#Put the marker in the desired position
def place_marker(board,marker,position):
    ''' Adjusts the Position of the marker in the board and calls the display_board function'''
    board[position]=marker
    display_board(board)


#Check all conditions that the mark has won
def win_check(board, mark):
    ''' Checks if there is a winner
    returns True if true and False if false'''
    #rows
    if(board[1]==board[2]==board[3]==mark) or (board[4]==board[5]==board[6]==mark) or (board[7]==board[8]==board[9]==mark):
        return True
    #Columns
    elif(board[1]==board[4]==board[7]==mark) or (board[2]==board[5]==board[8]==mark) or (board[3]==board[6]==board[9]==mark):
        return  True
    
    #Diagonals
    elif  (board[1]==board[5]==board[9]==mark) or (board[3]==board[5]==board[7]==mark):
        return True
    else:
        return False


#Decide which player to play first
import random as rn

def choose_first():
    ''' Chooses who starts first and returns who plays first and returns
    print player1 or player2'''
    luck=rn.randint(1,2)
    if luck==1:
        return 'player1'
    else:
        return 'player2'

#Return a bool whether there is a space in the board
def space_check(board, position):
    '''returns a boolean if there is a space or not'''
    if board[position] !=('X' or 'O'):
        return True
    else:
        return False

#Check whether the board is full and return a bool
def full_board_check(board):
    '''returns a boolean if the whole space is occupied'''
    nb=board[1:]
    for cell in nb:
        if (cell != 'X') and (cell != 'O'):
            return False
        
    else:
        return True

#Ask for the player's next position
def player_choice(board):
    '''asks for the position of the player and returns player1_pos and player2_ pos if there is a space'''
    player_pos=int(input('Player Position: '))
    if space_check(board,player_pos):
        x= player_pos
    return x

#Ask the player if they want to play again and return a bool
def replay():
    '''returns a boolean if the player wants to replay'''
    x= ''
    while (x.upper() != 'YES') and (x.upper() !='NO'):
        x=input('Replay: ')
    res=x.upper()
    if res =='YES':
        return True
    else:
        return False

#Use all defined functions to design the game
def TTT_game():
    print('Welcome to Tic Tac Toe!')
    print('-----------------------')
    
    while True:
        #set up the game
        
        ##assign variables of players markers and positions
        player1_marker,player2_marker= player_input()
        the_board=[" "]*10
        
        ##ask thm if they are ready to play
        ready=input('Are you ready to play (y or n): ').lower()
        if ready=='y':
            game_on=True
        else:
            print('press (shift + Enter) on the cell whenever you want to play')
            break
            
        ####assign turn to who will choose first
        turn = choose_first()
            
        ##while game on do the following
        while game_on:
            
            
            ###player1
            if turn=='player1':
                display_board(the_board)
                print('Randomly choosen player1')
                ####ask him his position
                print('player1 position: ')
                player1_pos=player_choice(the_board)
                
                ####keep printing the board
                place_marker(the_board,player1_marker,player1_pos)
                
                #### if not win the game nor tie => player2's turn
                if win_check(the_board,player1_marker) :
                    display_board(the_board)
                    print('Player1 wins!')
                    game_on=False
                else:
                    if full_board_check(the_board):
                        display_board(the_board)
                        print('Tie game')
                        game_on=False
                    else:
                        turn='player2'
            else:
                display_board(the_board)
                print('Randomly choosen player2')
                print('player2 position: ')
                player2_pos=player_choice(the_board)
                
                ####keep printing the board
                place_marker(the_board,player2_marker,player2_pos)
                
                #### if not win the game nor tie => player1's turn
                if win_check(the_board,player2_marker):
                    display_board(the_board)
                    print('Player2 wins!')
                    game_on=False
                else:
                    if full_board_check(the_board):
                        display_board(the_board)
                        print('Tie game')
                        game_on=False
                    else:
                        turn='player1'
            
        
        #replay?
        if not replay():
            print('press (shift + Enter) on the cell whenever you want to play')
            break


#Calling the TTT_game() function to play!
TTT_game()